package com.solvd.laba.db.model;

import java.util.Date;

public class Payment {
    private int id;
    private Date paymentDate;
    private double totalAmount;
    private int customerId;
    private int paymentMethodId;

    public Payment(int id, Date paymentDate, double totalAmount, int customerId, int paymentMethodId) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.paymentMethodId = paymentMethodId;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", paymentDate=" + paymentDate +
                ", totalAmount=" + totalAmount +
                ", customerId=" + customerId +
                ", paymentMethodId=" + paymentMethodId +
                '}'+"\n";
    }
}
