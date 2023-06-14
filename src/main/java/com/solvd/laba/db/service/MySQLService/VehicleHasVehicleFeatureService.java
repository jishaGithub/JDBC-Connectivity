package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLVehicleHasVehicleFeatureDAO;
import com.solvd.laba.db.model.VehicleHasVehicleFeature;
import com.solvd.laba.db.service.DAOService;

public class VehicleHasVehicleFeatureService implements DAOService<VehicleHasVehicleFeature> {
    private MySQLVehicleHasVehicleFeatureDAO vehicleHasVehicleFeatureDAO;

    public VehicleHasVehicleFeatureService(MySQLVehicleHasVehicleFeatureDAO vehicleHasVehicleFeatureDAO) {
        this.vehicleHasVehicleFeatureDAO = vehicleHasVehicleFeatureDAO;
    }

    @Override
    public void findById(int id) {
        vehicleHasVehicleFeatureDAO.get(id);
    }

    @Override
    public void selectAll() {
        vehicleHasVehicleFeatureDAO.get();
    }

    @Override
    public void newRow(VehicleHasVehicleFeature vehicleHasVehicleFeature) {
        vehicleHasVehicleFeatureDAO.add(vehicleHasVehicleFeature);
    }

    @Override
    public void updateRow(VehicleHasVehicleFeature vehicleHasVehicleFeature, int id) {
        vehicleHasVehicleFeatureDAO.update(vehicleHasVehicleFeature, id);
    }

    @Override
    public void deleteRow(int id) {
        vehicleHasVehicleFeatureDAO.delete(id);
    }
}
