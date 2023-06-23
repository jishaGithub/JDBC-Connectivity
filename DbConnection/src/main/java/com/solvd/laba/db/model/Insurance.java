package com.solvd.laba.db.model;

public class Insurance {
    private int id;
    private String insuranceName;
    private double coverage;
    private int customerId;

    public Insurance(int id, String insuranceName, double coverage, int customerId) {
        this.id = id;
        this.insuranceName = insuranceName;
        this.coverage = coverage;
        this.customerId = customerId;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInsuranceName() {
        return insuranceName;
    }

    public void setInsuranceName(String insuranceName) {
        this.insuranceName = insuranceName;
    }

    public double getCoverage() {
        return coverage;
    }

    public void setCoverage(double coverage) {
        this.coverage = coverage;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", insuranceName='" + insuranceName + '\'' +
                ", coverage=" + coverage +
                ", customerId=" + customerId +
                '}'+"\n";
    }
}
