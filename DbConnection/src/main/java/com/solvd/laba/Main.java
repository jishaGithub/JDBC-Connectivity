package com.solvd.laba;

import com.solvd.laba.db.connection.ConfigFileDAO;
import com.solvd.laba.db.dao.MySQLDAO.*;
import com.solvd.laba.db.model.CustomerAuthentication;
import com.solvd.laba.db.model.Employee;
import com.solvd.laba.db.service.MySQLService.*;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService(new MySQLEmployeeDAO());
        employeeService.findById(2);
        employeeService.selectAll();
        employeeService.updateRow(new Employee("Jacob","jacob@gmail.com",12),6);
        employeeService.deleteRow(7);
        BranchService branchService = new BranchService(new MySQLBranchDAO());
        branchService.findById(3);
        branchService.selectAll();
        branchService.deleteRow(12);
        CustomerService customerService = new CustomerService(new MySQLCustomerDAO());
        customerService.findById(3);
        customerService.selectAll();
        CustomerAuthenticationService customerAuthenticationService = new CustomerAuthenticationService(new MySQLCustomerAuthenticationDAO());
        customerAuthenticationService.selectAll();
        customerAuthenticationService.updateRow(new CustomerAuthentication("jd2023","5634",4),4);
        InsuranceService insuranceService = new InsuranceService(new MySQLInsuranceDAO());
        insuranceService.selectAll();
        insuranceService.deleteRow(6);
        PaymentService paymentService = new PaymentService(new MySQLPaymentDAO());
        paymentService.findById(3);
        paymentService.selectAll();
        paymentService.deleteRow(15);
        PaymentMethodService paymentMethodService = new PaymentMethodService(new MySQLPaymentMethodDAO());
        paymentMethodService.selectAll();
        PromotionService promotionService = new PromotionService(new MySQLPromotionDAO());
        promotionService.selectAll();
        RentalService rentalService = new RentalService(new MySQLRentalDAO());
        rentalService.selectAll();
        RentalRateService rentalRateService = new RentalRateService(new MySQLRentalRateDAO());
        rentalRateService.selectAll();
        VehicleCategoryService vehicleCategoryService = new VehicleCategoryService(new MySQLVehicleCategoryDAO());
        vehicleCategoryService.selectAll();
        VehicleFeatureService vehicleFeatureService = new VehicleFeatureService(new MySQLVehicleFeatureDAO());
        vehicleFeatureService.selectAll();
        VehicleHasVehicleFeatureService vehicleHasVehicleFeatureService = new VehicleHasVehicleFeatureService(new MySQLVehicleHasVehicleFeatureDAO());
        vehicleHasVehicleFeatureService.selectAll();
        try {
            ConfigFileDAO.getDataSource().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
