package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLCustomerDAO;
import com.solvd.laba.db.model.Customer;
import com.solvd.laba.db.service.DAOService;

public class CustomerService implements DAOService<Customer> {
    private MySQLCustomerDAO customerDAO;

    public CustomerService(MySQLCustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void findById(int id) {
        customerDAO.get(id);
    }

    @Override
    public void selectAll() {
        customerDAO.get();
    }

    @Override
    public void newRow(Customer customer) {
        customerDAO.add(customer);
    }

    @Override
    public void updateRow(Customer customer, int id) {
        customerDAO.update(customer, id);
    }

    @Override
    public void deleteRow(int id) {
        customerDAO.delete(id);
    }
}
