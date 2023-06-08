package com.solvd.laba.db.model;

public class VehicleFeature {
    private int id;
    private String name;
    private String availability;
    private Double additionalCost;

    public VehicleFeature(int id, String name, String availability, Double additionalCost) {
        this.id = id;
        this.name = name;
        this.availability = availability;
        this.additionalCost = additionalCost;
    }

    public VehicleFeature(String name, String availability, Double additionalCost) {
        this.name = name;
        this.availability = availability;
        this.additionalCost = additionalCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public Double getAdditionalCost() {
        return additionalCost;
    }

    public void setAdditionalCost(Double additionalCost) {
        this.additionalCost = additionalCost;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", availability='" + availability + '\'' +
                ", additionalCost=" + additionalCost +
                '}'+"\n";
    }
}
