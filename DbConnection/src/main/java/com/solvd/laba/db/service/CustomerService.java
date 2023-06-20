package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.CustomerDAO;
import com.solvd.laba.db.model.Customer;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class CustomerService implements GenericDAO<Customer> {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    private CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public Customer get(int id) {
        return customerDAO.get(id);
    }

    @Override
    public ArrayList<Customer> get() {
        return customerDAO.get();
    }

    @Override
    public void add(Customer customer) {
        customerDAO.add(customer);
    }

    @Override
    public void update(Customer customer, int id) {
        customerDAO.update(customer, id);
    }

    @Override
    public void delete(int id) {
        customerDAO.delete(id);
    }
}
