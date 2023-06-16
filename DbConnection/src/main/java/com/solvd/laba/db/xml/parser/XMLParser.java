package com.solvd.laba.db.xml.parser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class XMLParser {
    private static final Logger logger = LogManager.getLogger(XMLParser.class);
    public void domXmlParser() {
        try {
            File xmlFile= new File("src/main/resources/carRentalService.xml");
            logger.info("\nParsing "+xmlFile+" using DOM parser:");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(xmlFile);
            Element carRentalService = document.getDocumentElement();
            String rootElement = carRentalService.getNodeName();
            logger.info("Root element: " + rootElement);
            NodeList vehicles = carRentalService.getElementsByTagName("vehicle");
            logger.info("Vehicles");
            for (int i = 0; i < vehicles.getLength(); i++) {
                Element vehicle = (Element) vehicles.item(i);
                int id = Integer.parseInt(vehicle.getAttribute("id"));
                String brand = vehicle.getElementsByTagName("brand").item(0).getTextContent();
                String model = vehicle.getElementsByTagName("model").item(0).getTextContent();
                String year = vehicle.getElementsByTagName("year").item(0).getTextContent();
                String color = vehicle.getElementsByTagName("color").item(0).getTextContent();
                int mileage = Integer.parseInt(vehicle.getElementsByTagName("mileage").item(0).getTextContent());
                int licenseNo = Integer.parseInt(vehicle.getElementsByTagName("licenseNo").item(0).getTextContent());
                logger.info("   Vehicle = " + id +" "+ brand + " " + model + " " + year + " " + color + " " + mileage + " " + licenseNo);
            }
            NodeList customers = carRentalService.getElementsByTagName("customer");
            logger.info("Customers");
            for (int i = 0; i < customers.getLength(); i++) {
                Element customer = (Element) customers.item(i);
                int id = Integer.parseInt(customer.getAttribute("id"));
                String firstName = customer.getElementsByTagName("firstName").item(0).getTextContent();
                String lastName = customer.getElementsByTagName("lastName").item(0).getTextContent();
                String customerEmail = customer.getElementsByTagName("customerEmail").item(0).getTextContent();
                long phoneNumber = Long.parseLong(customer.getElementsByTagName("phoneNumber").item(0).getTextContent());
                int age = Integer.parseInt(customer.getElementsByTagName("age").item(0).getTextContent());
                logger.info("   Customer = "  + id + " " + firstName + " " + lastName + " " + customerEmail + " " + phoneNumber +" "+ age);
            }
            NodeList rentals = carRentalService.getElementsByTagName("rental");
            logger.info("Rentals");
            for (int i = 0; i < rentals.getLength(); i++) {
                Element rental = (Element) rentals.item(i);
                int id = Integer.parseInt(rental.getAttribute("id"));
                int vehicleId = Integer.parseInt(rental.getElementsByTagName("vehicleId").item(0).getTextContent());
                int customerId = Integer.parseInt(rental.getElementsByTagName("customerId").item(0).getTextContent());
                String startDate = rental.getElementsByTagName("rentalDate").item(0).getTextContent();
                String endDate = rental.getElementsByTagName("returnDate").item(0).getTextContent();
                logger.info("   Rental = " + id + " " + vehicleId + " " + customerId + " " + startDate + " " + endDate);
            }
            NodeList authentications = carRentalService.getElementsByTagName("customerAuthentication");
            logger.info("Authentications");
            for (int i = 0; i < authentications.getLength(); i++) {
                Element authentication = (Element) authentications.item(i);
                int id = Integer.parseInt(authentication.getAttribute("id"));
                String userName = authentication.getElementsByTagName("userName").item(0).getTextContent();
                String password = authentication.getElementsByTagName("password").item(0).getTextContent();
                int customerId = Integer.parseInt(authentication.getElementsByTagName("customerId").item(0).getTextContent());
                logger.info("   Authentication = " + id + " " + userName + " " + password + " " + customerId);
            }
            NodeList employees = carRentalService.getElementsByTagName("employee");
            logger.info("Employees");
            for (int i = 0; i < employees.getLength(); i++) {
                Element employee = (Element) employees.item(i);
                int id = Integer.parseInt(employee.getAttribute("id"));
                String name = employee.getElementsByTagName("name").item(0).getTextContent();
                String employeeEmail = employee.getElementsByTagName("employeeEmail").item(0).getTextContent();
                int branchId = Integer.parseInt(employee.getElementsByTagName("branchId").item(0).getTextContent());
                logger.info("   Employee = " + id + " " + name + " " + employeeEmail + " " + branchId);
            }
        } catch (ParserConfigurationException | IOException | SAXException pce) {
            logger.error(pce.getMessage());
        }
    }
}
