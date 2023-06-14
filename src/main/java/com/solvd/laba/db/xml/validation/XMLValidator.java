package com.solvd.laba.db.xml.validation;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.XMLConstants;
import javax.xml.parsers.*;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stax.StAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.*;

public class XMLValidator {
    private static final Logger logger = LogManager.getLogger(XMLValidator.class);

    public static Schema getSchema(String xmlFile, String schemaFile) {
        try {
            SchemaFactory schemaFactory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            return schemaFactory.newSchema(new File(schemaFile));
        } catch (SAXException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

        public boolean domValidator(String xmlFile, String schemaFile) {
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            documentBuilderFactory.setValidating(true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            documentBuilder.setErrorHandler(new DefaultHandler());
            Document document = documentBuilder.parse(xmlFile);
            Schema rentalSchema = getSchema(xmlFile,schemaFile);
            Validator validator = rentalSchema.newValidator();
            validator.validate(new DOMSource(document));
            logger.info("Valid xml - validated using DOM parser");
        } catch (ParserConfigurationException pce) {
            logger.error("Invalid XML: "+pce.getMessage());
            return false;
        } catch (IOException ioe) {
            logger.error("Invalid IO error: "+ ioe.getMessage());
            return false;
        } catch (SAXException se) {
            logger.error(se.getMessage());
            return false;
        }
        return true;
    }

    public boolean SAXValidator(String xmlFile, String schemaFile) {
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParserFactory.setValidating(true);
            Schema rentalSchema = getSchema(xmlFile,schemaFile);
            saxParserFactory.setSchema(rentalSchema);
            SAXParser saxParser = saxParserFactory.newSAXParser();
            saxParser.parse(new File(xmlFile), new DefaultHandler());
            Validator validator = rentalSchema.newValidator();
            validator.validate(new StreamSource(new File(xmlFile)));
            logger.info("Validation complete using SAX parser. Valid XML");
        } catch (SAXException se) {
            logger.error("Invalid XML: " + se.getMessage());
            return false;
        } catch (IOException ioe) {
            logger.error("IO error: " + ioe.getMessage());
            return false;
        } catch (ParserConfigurationException pce) {
            logger.error("Invalid XML:" + pce.getMessage());
            return false;
        }
        return true;
    }

    public boolean staxValidation(String xmlFile, String schemaFile) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(xmlFile));
            Schema rentalSchema = getSchema(xmlFile,schemaFile);
            Validator validator = rentalSchema.newValidator();
            validator.validate(new StAXSource(xmlStreamReader));
            while(xmlStreamReader.hasNext()) {
                xmlStreamReader.next();
            }
            xmlStreamReader.close();
            logger.info("Valid XML - validation using stax parser");
        } catch (FileNotFoundException fnfe) {
            logger.error("Input file error: "+fnfe.getMessage());
            return false;
        } catch (IOException ioe) {
            logger.error("IO error: "+ioe.getMessage());
            return false;
        } catch (SAXException | XMLStreamException se) {
            logger.error("Invalid XML error: "+se.getMessage());
            return false;
        }
        return true;        
    }
}
