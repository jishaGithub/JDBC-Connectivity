package com.solvd.laba.db.xml.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.solvd.laba.db.model.*;
import com.solvd.laba.db.xml.jaxb.CarRentalService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

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
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            objectMapper.setDateFormat(simpleDateFormat);
            CarRentalService carRentalServiceJackson = objectMapper.readValue(fileInputStream, CarRentalService.class);
            for (Customer customer : carRentalServiceJackson.getCustomers()) {
                logger.info(customer);
            }
            for (CustomerAuthentication customerAuthentication : carRentalServiceJackson.getCustomerAuthentications()) {
                logger.info(customerAuthentication);
            }
            for (Vehicle vehicle : carRentalServiceJackson.getVehicles()) {
                logger.info(vehicle);
            }
            for (Rental rental : carRentalServiceJackson.getRentals()) {
                String rentalAsString = objectMapper.writeValueAsString(rental);
                logger.info(rentalAsString);
            }
            for (Employee employee : carRentalServiceJackson.getEmployees()) {
                logger.info(employee);
            }
        } catch (IOException e) {
            logger.error("Error occurred while parsing JSON: " + e.getMessage());
        }
    }
}
