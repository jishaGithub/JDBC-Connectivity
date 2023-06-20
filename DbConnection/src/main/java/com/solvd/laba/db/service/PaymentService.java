package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.PaymentDAO;
import com.solvd.laba.db.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class PaymentService implements GenericDAO<Payment> {
    private static final Logger logger = LogManager.getLogger(PaymentService.class);
    private PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public Payment get(int id) {
        return paymentDAO.get(id);
    }

    @Override
    public ArrayList<Payment> get() {
        return paymentDAO.get();
    }

    @Override
    public void add(Payment payment) {
        paymentDAO.add(payment);
    }

    @Override
    public void update(Payment payment, int id) {
        paymentDAO.update(payment, id);
    }

    @Override
    public void delete(int id) {
        paymentDAO.delete(id);
    }
}
