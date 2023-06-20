package com.solvd.laba.db.xml.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.db.xml.jaxb.CarRentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;

public class JacksonParser {
    Logger logger = LogManager.getLogger(JacksonParser.class);
    public void jsonParser(String jsonFileName) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CarRentalService deSerializeJackson = objectMapper.readValue(new File(jsonFileName),CarRentalService.class);
            logger.info(deSerializeJackson);
        } catch (IOException e) {
            logger.info(e.getMessage());
        }
    }
}
