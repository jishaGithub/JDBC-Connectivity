package com.solvd.laba.db.model;

public class Vehicle {
    private int id;
    private String brand;
    private String model;
    private int year;
    private String color;
    private int mileage;
    private String licenseNumber;
    private int categoryId;
    private int customerId;

    public Vehicle(String brand, String model, int year, String color, int mileage, String licenseNumber,
                   int categoryId, int customerId) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.licenseNumber = licenseNumber;
        this.categoryId = categoryId;
        this.customerId = customerId;
    }

    public Vehicle(int id, String brand, String model, int year, String color, int mileage, String licenseNumber,
                   int categoryId, int customerId) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.color = color;
        this.mileage = mileage;
        this.licenseNumber = licenseNumber;
        this.categoryId = categoryId;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getMileage() {
        return mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public String getLicenseNum() {
        return licenseNumber;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNumber = licenseNum;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String toString() {
        return "{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", color='" + color + '\'' +
                ", mileage=" + mileage +
                ", licenseNumber='" + licenseNumber + '\'' +
                ", categoryId=" + categoryId +
                ", customerId=" + customerId +
                '}'+"\n";
    }
}
