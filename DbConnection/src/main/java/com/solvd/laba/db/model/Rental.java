package com.solvd.laba.db.model;

import java.util.Date;

public class Rental {
    private int id;
    private Date rentalDate;
    private Date returnDate;
    private int customerId;
    private int insuranceId;
    private int rentalRateId;
    private int vehicleId;
    private int promotionId;
    private int employeeId;

    public Rental(int id, Date rentalDate, Date returnDate, int customerId, int insuranceId,
                  int rentalRateId, int vehicleId, int promotionId, int employeeId) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.rentalRateId = rentalRateId;
        this.vehicleId = vehicleId;
        this.promotionId = promotionId;
        this.employeeId = employeeId;
    }

    public Rental(Date rentalDate, Date returnDate, int customerId, int insuranceId,
                  int rentalRateId, int vehicleId, int promotionId, int employeeId) {
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.rentalRateId = rentalRateId;
        this.vehicleId = vehicleId;
        this.promotionId = promotionId;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getRentalRateId() {
        return rentalRateId;
    }

    public void setRentalRateId(int rentalRateId) {
        this.rentalRateId = rentalRateId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", customerId=" + customerId +
                ", insuranceId=" + insuranceId +
                ", rentalRateId=" + rentalRateId +
                ", vehicleId=" + vehicleId +
                ", promotionId=" + promotionId +
                ", employeeId=" + employeeId +
                '}'+"\n";
    }
}
