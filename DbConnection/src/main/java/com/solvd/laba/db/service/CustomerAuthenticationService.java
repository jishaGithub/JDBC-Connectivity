package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.CustomerAuthenticationDAO;
import com.solvd.laba.db.model.CustomerAuthentication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class CustomerAuthenticationService implements GenericDAO<CustomerAuthentication> {
    private CustomerAuthenticationDAO customerAuthenticationDAO;

    public CustomerAuthenticationService(CustomerAuthenticationDAO customerAuthenticationDAO) {
        this.customerAuthenticationDAO = customerAuthenticationDAO;
    }

    @Override
    public CustomerAuthentication get(int id) {
        return customerAuthenticationDAO.get(id);
    }

    @Override
    public ArrayList<CustomerAuthentication> get() {
        return customerAuthenticationDAO.get();
    }

    @Override
    public void add(CustomerAuthentication customerAuthentication) {
        customerAuthenticationDAO.add(customerAuthentication);
    }

    @Override
    public void update(CustomerAuthentication customerAuthentication, int id) {
        customerAuthenticationDAO.update(customerAuthentication, id);
    }

    @Override
    public void delete(int id) {
        customerAuthenticationDAO.delete(id);
    }
}
