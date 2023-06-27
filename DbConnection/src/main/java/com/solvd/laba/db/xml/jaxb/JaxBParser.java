package com.solvd.laba.db.xml.jaxb;

import com.solvd.laba.db.model.*;
import jakarta.xml.bind.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.util.List;

public class JaxBParser {
    private static final Logger logger = LogManager.getLogger(JaxBParser.class);
    
    public void marshalToXml() {
        Customer customer = new Customer(1,"Sam","Varghese","sam@gmail.com","3456782345",23);
        try {
            JAXBContext context = JAXBContext.newInstance(Customer.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,true);
            File customerXml = new File("src/main/resources/sampleCustomer.xml");
            marshaller.marshal(customer,customerXml);
        } catch (JAXBException e) {
            logger.error(e.getMessage());            
        }
    }

    public void parseXmlJaxB(String xmlFile) {
        JAXBContext context;
        try {
            context = JAXBContext.newInstance(CarRentalService.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CarRentalService carRentalService = (CarRentalService) unmarshaller.unmarshal(new File(xmlFile));
            List<Vehicle> vehicleList = carRentalService.getVehicles();
            List<Customer> customerList = carRentalService.getCustomers();
            List<CustomerAuthentication> customerAuthenticationList = carRentalService.getCustomerAuthentications();
            List<Employee> employeeList = carRentalService.getEmployees();
            List<Rental> rentalList = carRentalService.getRentals();
            logger.info("Parsing XML using JaxB");
            logger.info(customerList);
            logger.info(employeeList);
            logger.info(rentalList);
            logger.info(customerAuthenticationList);
            logger.info(vehicleList);
        } catch (JAXBException jbe) {
            logger.error("Error: " + jbe.getMessage());
        }
    }
}
