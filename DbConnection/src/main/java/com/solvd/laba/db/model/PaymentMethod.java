package com.solvd.laba.db.model;

public class PaymentMethod {
    private int id;
    private String paymentType;

    public PaymentMethod(int id, String paymentType) {
        this.id = id;
        this.paymentType = paymentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", paymentType='" + paymentType + '\'' +
                '}'+"\n";
    }
}
