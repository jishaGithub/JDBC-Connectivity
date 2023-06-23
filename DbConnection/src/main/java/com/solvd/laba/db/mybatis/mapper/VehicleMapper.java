package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Vehicle;

import java.util.List;

public interface VehicleMapper {
    void insertVehicle(Vehicle vehicle);

    void updateVehicle(Vehicle vehicle);

    void deleteVehicle(int vehicleId);

    Vehicle getVehicleById(int vehicleId);

    List<Vehicle> getAllVehicles();
}
