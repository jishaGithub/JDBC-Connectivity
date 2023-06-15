package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLCustomerAuthenticationDAO;
import com.solvd.laba.db.model.CustomerAuthentication;
import com.solvd.laba.db.service.DAOService;

public class CustomerAuthenticationService implements DAOService<CustomerAuthentication> {
    private MySQLCustomerAuthenticationDAO customerAuthenticationDAO;

    public CustomerAuthenticationService(MySQLCustomerAuthenticationDAO customerAuthenticationDAO) {
        this.customerAuthenticationDAO = customerAuthenticationDAO;
    }

    @Override
    public void findById(int id) {
        customerAuthenticationDAO.get(id);
    }

    @Override
    public void selectAll() {
        customerAuthenticationDAO.get();
    }

    @Override
    public void newRow(CustomerAuthentication customerAuthentication) {
        customerAuthenticationDAO.add(customerAuthentication);
    }

    @Override
    public void updateRow(CustomerAuthentication customerAuthentication, int id) {
        customerAuthenticationDAO.update(customerAuthentication, id);
    }

    @Override
    public void deleteRow(int id) {
        customerAuthenticationDAO.delete(id);
    }
}
