package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLPaymentDAO;
import com.solvd.laba.db.model.Payment;
import com.solvd.laba.db.service.DAOService;

public class PaymentService implements DAOService<Payment> {
    private MySQLPaymentDAO paymentDAO;

    public PaymentService(MySQLPaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public void findById(int id) {
        paymentDAO.get(id);
    }

    @Override
    public void selectAll() {
        paymentDAO.get();
    }

    @Override
    public void newRow(Payment payment) {
        paymentDAO.add(payment);
    }

    @Override
    public void updateRow(Payment payment, int id) {
        paymentDAO.update(payment, id);
    }

    @Override
    public void deleteRow(int id) {
        paymentDAO.delete(id);
    }
}
