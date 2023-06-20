package com.solvd.laba.db.xml.jaxb;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.solvd.laba.db.model.*;
import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "carRentalService")
@JsonRootName("carRentalService")
public class CarRentalService {    
    @XmlElement(name ="id")
    private int id;
    @XmlElementWrapper(name="customers")
    @XmlElement(name="customer")
    @JsonProperty("customers")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Customer> customers;
    @XmlElementWrapper(name="customerAuthentications")
    @XmlElement(name="customerAuthentication")
    @JsonProperty("customerAuthentications")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<CustomerAuthentication> customerAuthentications;
    @XmlElementWrapper(name="employees")
    @XmlElement(name="employee")
    @JsonProperty("employees")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Employee> employees;
    @XmlElementWrapper(name="rentals")
    @XmlElement(name="rental")
    @JsonProperty("rentals")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private List<Rental> rentals;
    @XmlElementWrapper(name="vehicles")
    @XmlElement(name="vehicle")
    @JsonProperty("vehicles")
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
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

    @Override
    public String toString() {
        return "\nCarRentalService = {" + "\n"+
                "customers=" + customers +"\n"+
                ", customerAuthentications=" + customerAuthentications + "\n"+
                ", employees=" + employees + "\n"+
                ", rentals=" + rentals + "\n"+
                ", vehicles=" + vehicles + "\n"+
                "}";
    }
}
