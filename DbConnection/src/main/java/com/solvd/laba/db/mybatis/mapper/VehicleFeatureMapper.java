package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.VehicleFeature;

import java.util.List;

public interface VehicleFeatureMapper {
    void insertVehicleFeature(VehicleFeature vehicleFeature);

    void updateVehicleFeature(VehicleFeature vehicleFeature);

    void deleteVehicleFeature(int vehicleFeatureId);

    VehicleFeature getVehicleFeatureById(int vehicleFeatureId);

    List<VehicleFeature> getAllVehicleFeatures();
}
