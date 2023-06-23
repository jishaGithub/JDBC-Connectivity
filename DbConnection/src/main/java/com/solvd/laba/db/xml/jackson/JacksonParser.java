package com.solvd.laba.db.xml.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.db.xml.jaxb.CarRentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class JacksonParser {
    private static final Logger logger = LogManager.getLogger(JacksonParser.class);

    public void jsonParser(String jsonFileName) {
        File jsonFile = new File(jsonFileName);
        if (!jsonFile.exists()) {
            logger.error("JSON file does not exist");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream(jsonFile)) {
            ObjectMapper objectMapper = new ObjectMapper();
            CarRentalService deSerializeJackson = objectMapper.readValue(fileInputStream, CarRentalService.class);
            logger.info(deSerializeJackson);
        } catch (IOException e) {
            logger.error("Error occurred while parsing JSON: " + e.getMessage());
        }
    }
}
