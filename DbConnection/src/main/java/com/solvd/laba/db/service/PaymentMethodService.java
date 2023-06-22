package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.PaymentMethodDAO;
import com.solvd.laba.db.model.PaymentMethod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class PaymentMethodService implements GenericDAO<PaymentMethod> {    
    private PaymentMethodDAO paymentMethodDAO;

    public PaymentMethodService(PaymentMethodDAO paymentMethodDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
    }

    @Override
    public PaymentMethod get(int id) {
        return paymentMethodDAO.get(id);
    }

    @Override
    public ArrayList<PaymentMethod> get() {
        return paymentMethodDAO.get();
    }

    @Override
    public void add(PaymentMethod paymentMethod) {
        paymentMethodDAO.add(paymentMethod);
    }

    @Override
    public void update(PaymentMethod paymentMethod, int id) {
        paymentMethodDAO.update(paymentMethod, id);
    }

    @Override
    public void delete(int id) {
        paymentMethodDAO.delete(id);
    }
}
