package com.solvd.laba.db.model;

public class RentalRate {
    private int id;
    private String rentalType;
    private double rentalAmount;
    private Integer minDuration;
    private Integer maxDuration;

    public RentalRate(int id, String rentalType, double rentalAmount, Integer minDuration, Integer maxDuration) {
        this.id = id;
        this.rentalType = rentalType;
        this.rentalAmount = rentalAmount;
        this.minDuration = minDuration;
        this.maxDuration = maxDuration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public double getRentalAmount() {
        return rentalAmount;
    }

    public void setRentalAmount(double rentalAmount) {
        this.rentalAmount = rentalAmount;
    }

    public Integer getMinDuration() {
        return minDuration;
    }

    public void setMinDuration(Integer minDuration) {
        this.minDuration = minDuration;
    }

    public Integer getMaxDuration() {
        return maxDuration;
    }

    public void setMaxDuration(Integer maxDuration) {
        this.maxDuration = maxDuration;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", rentalType='" + rentalType + '\'' +
                ", rentalAmount=" + rentalAmount +
                ", minDuration=" + minDuration +
                ", maxDuration=" + maxDuration +
                '}'+"\n";
    }
}
