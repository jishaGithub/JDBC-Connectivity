package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.PaymentMethod;

import java.util.List;

public interface PaymentMethodMapper {
    void insertPaymentMethod(PaymentMethod paymentMethod);

    void updatePaymentMethod(PaymentMethod paymentMethod);

    void deletePaymentMethod(int paymentMethodId);

    PaymentMethod getPaymentMethodById(int paymentMethodId);

    List<PaymentMethod> getAllPaymentMethods();
}
