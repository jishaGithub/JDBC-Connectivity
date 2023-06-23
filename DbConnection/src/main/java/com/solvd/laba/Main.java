package com.solvd.laba;

import com.solvd.laba.db.dao.*;
import com.solvd.laba.db.model.Customer;
import com.solvd.laba.db.model.CustomerAuthentication;
import com.solvd.laba.db.model.Employee;
import com.solvd.laba.db.mybatis.MyBatisConfig;
import com.solvd.laba.db.service.jdbc.*;
import com.solvd.laba.db.service.mybatis.*;
import com.solvd.laba.db.util.ConfigFileDAO;
import com.solvd.laba.db.xml.jackson.JacksonParser;
import com.solvd.laba.db.xml.parser.XMLParser;
import com.solvd.laba.db.xml.validation.XMLValidator;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {

        Logger logger = LogManager.getLogger(Main.class);
        EmployeeService employeeService = new EmployeeService(new EmployeeDAO());
        employeeService.get(4);
        employeeService.update(new Employee(15,"Sutton","sutton@gmail.com",13),15);
        employeeService.delete(7);
        BranchService branchService = new BranchService(new BranchDAO());
        branchService.get(10);
        branchService.delete(12);
        CustomerService customerService = new CustomerService(new CustomerDAO());
        customerService.get(17);
        CustomerAuthenticationService customerAuthenticationService = new CustomerAuthenticationService(new CustomerAuthenticationDAO());
        customerAuthenticationService.get();
        customerAuthenticationService.update(new CustomerAuthentication(4, "jd2023", "5634", 4),4);
        InsuranceService insuranceService = new InsuranceService(new InsuranceDAO());
        insuranceService.delete(6);
        PaymentService paymentService = new PaymentService(new PaymentDAO());
        paymentService.get(3);
        paymentService.delete(15);
        PaymentMethodService paymentMethodService = new PaymentMethodService(new PaymentMethodDAO());
        paymentMethodService.get();
        PromotionService promotionService = new PromotionService(new PromotionDAO());
        promotionService.get(2);
        RentalService rentalService = new RentalService(new RentalDAO());
        rentalService.get();
        RentalRateService rentalRateService = new RentalRateService(new RentalRateDAO());
        rentalRateService.get(5);
        VehicleCategoryService vehicleCategoryService = new VehicleCategoryService(new VehicleCategoryDAO());
        vehicleCategoryService.get(5);
        VehicleFeatureService vehicleFeatureService = new VehicleFeatureService(new VehicleFeatureDAO());
        vehicleFeatureService.get();
        VehicleHasVehicleFeatureService vehicleHasVehicleFeatureService = new VehicleHasVehicleFeatureService(new VehicleHasVehicleFeatureDAO());
        vehicleHasVehicleFeatureService.get(3);
        try {
            ConfigFileDAO.getDataSource().close();
        } catch (SQLException e) {
            logger.info(e.getMessage());
        }
        String xmlFileName = "src/main/resources/carRentalService.xml";
        String schemaFileName = "src/main/resources/carRentalService.xsd";
        XMLValidator xmlValidator = new XMLValidator();
        xmlValidator.domValidator(xmlFileName, schemaFileName);
        xmlValidator.SAXValidator(xmlFileName, schemaFileName);
        xmlValidator.staxValidation(xmlFileName, schemaFileName);
        XMLParser xmlParser = new XMLParser();
        xmlParser.domXmlParser();
        String jsonFileName = "src/main/resources/CarRentalService.json";
        JacksonParser jacksonParser = new JacksonParser();
        jacksonParser.jsonParser(jsonFileName);
        try (SqlSession sqlSession = MyBatisConfig.openSession()) {
            MyBatisCustomerService myBatisCustomerService = new MyBatisCustomerService(sqlSession);
            myBatisCustomerService.getAllCustomers();
            myBatisCustomerService.getCustomerById(15);
            myBatisCustomerService.insertCustomer( new Customer("Mathew", "Thomas", "matt@gmail.com", "6784563423", 40));
            Customer customer = myBatisCustomerService.getCustomerById(102);
            customer.setFirstName("Lilly");
            customer.setLastName("Johnson");
            customer.setEmail("lily@gmail.com");
            customer.setPhoneNumber("5673452345");
            customer.setAge(25);
            myBatisCustomerService.updateCustomer(customer);
            myBatisCustomerService.deleteCustomer(14);
            MyBatisBranchLocationService myBatisBranchLocationService = new MyBatisBranchLocationService(sqlSession);
            myBatisBranchLocationService.getBranchLocationById(3);
            MyBatisPaymentMethodService myBatisPaymentMethodService = new MyBatisPaymentMethodService(sqlSession);
            myBatisPaymentMethodService.getPaymentMethodById(4);
            myBatisPaymentMethodService.getAllPaymentMethods();
            MyBatisEmployeeService myBatisEmployeeService = new MyBatisEmployeeService(sqlSession);
            Employee employee = myBatisEmployeeService.getEmployeeById(12);
            employee.setName("Tracy Tram");
            employee.setEmail("tracyy@gmail.com");
            employee.setBranchId(12);
            myBatisEmployeeService.updateEmployee(employee);
            myBatisEmployeeService.getAllEmployees();
            sqlSession.commit();
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
    }
}