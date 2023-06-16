package com.solvd.laba.db.xml.jaxb;

import com.solvd.laba.db.model.*;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "carRentalService")
public class CarRentalService {
    @XmlElement(name ="id")
    private int id;
    @XmlElementWrapper(name="customers")
    @XmlElement(name="customer")
    private List<Customer> customers;
    @XmlElementWrapper(name="customerAuthentications")
    @XmlElement(name="customerAuthentication")
    private List<CustomerAuthentication> customerAuthentications;
    @XmlElementWrapper(name="employees")
    @XmlElement(name="employee")
    private List<Employee> employees;
    @XmlElementWrapper(name="rentals")
    @XmlElement(name="rental")
    private List<Rental> rentals;
    @XmlElementWrapper(name="vehicles")
    @XmlElement(name="vehicle")
    private List<Vehicle> vehicles;

    public CarRentalService() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicle(List<Vehicle> vehicle) {
        this.vehicles = vehicles;
    }

    public List<CustomerAuthentication> getCustomerAuthentications() {
        return customerAuthentications;
    }

    public void setCustomerAuthentications(List<CustomerAuthentication> customerAuthentications) {
        this.customerAuthentications = customerAuthentications;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
