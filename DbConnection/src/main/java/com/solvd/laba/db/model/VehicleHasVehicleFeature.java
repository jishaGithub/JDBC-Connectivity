package com.solvd.laba.db.model;

public class VehicleHasVehicleFeature {
    private int vehicleId;
    private int vehicleFeatureId;

    public VehicleHasVehicleFeature(int vehicleId, int vehicleFeatureId) {
        this.vehicleId = vehicleId;
        this.vehicleFeatureId = vehicleFeatureId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getVehicleFeatureId() {
        return vehicleFeatureId;
    }

    public void setVehicleFeatureId(int vehicleFeatureId) {
        this.vehicleFeatureId = vehicleFeatureId;
    }

    @Override
    public String toString() {
        return "{" +
                "vehicleId=" + vehicleId +
                ", vehicleFeatureId=" + vehicleFeatureId +
                '}'+"\n";
    }
}
