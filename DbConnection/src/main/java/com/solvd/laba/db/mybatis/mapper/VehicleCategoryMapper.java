package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.VehicleCategory;

import java.util.List;

public interface VehicleCategoryMapper {
    void insertVehicleCategory(VehicleCategory vehicleCategory);

    void updateVehicleCategory(VehicleCategory vehicleCategory);

    void deleteVehicleCategory(int vehicleCategoryId);

    VehicleCategory getVehicleCategoryById(int vehicleCategoryId);

    List<VehicleCategory> getAllVehicleCategories();
}
