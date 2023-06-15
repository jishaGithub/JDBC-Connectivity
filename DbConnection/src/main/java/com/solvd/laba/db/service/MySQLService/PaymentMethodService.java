package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLPaymentMethodDAO;
import com.solvd.laba.db.model.PaymentMethod;
import com.solvd.laba.db.service.DAOService;

public class PaymentMethodService implements DAOService<PaymentMethod> {
    private MySQLPaymentMethodDAO paymentMethodDAO;

    public PaymentMethodService(MySQLPaymentMethodDAO paymentMethodDAO) {
        this.paymentMethodDAO = paymentMethodDAO;
    }

    @Override
    public void findById(int id) {
        paymentMethodDAO.get(id);
    }

    @Override
    public void selectAll() {
        paymentMethodDAO.get();
    }

    @Override
    public void newRow(PaymentMethod paymentMethod) {
        paymentMethodDAO.add(paymentMethod);
    }

    @Override
    public void updateRow(PaymentMethod paymentMethod, int id) {
        paymentMethodDAO.update(paymentMethod, id);
    }

    @Override
    public void deleteRow(int id) {
        paymentMethodDAO.delete(id);
    }
}
