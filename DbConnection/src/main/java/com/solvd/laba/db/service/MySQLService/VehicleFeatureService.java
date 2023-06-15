package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLVehicleFeatureDAO;
import com.solvd.laba.db.model.VehicleFeature;
import com.solvd.laba.db.service.DAOService;

public class VehicleFeatureService implements DAOService<VehicleFeature> {
    private MySQLVehicleFeatureDAO vehicleFeatureDAO;
    public VehicleFeatureService(MySQLVehicleFeatureDAO vehicleFeatureDAO) {
        this.vehicleFeatureDAO = vehicleFeatureDAO;
    }

    @Override
    public void findById(int id) {
        vehicleFeatureDAO.get(id);
    }

    @Override
    public void selectAll() {
        vehicleFeatureDAO.get();
    }

    @Override
    public void newRow(VehicleFeature vehicleFeature) {
        vehicleFeatureDAO.add(vehicleFeature);
    }

    @Override
    public void updateRow(VehicleFeature vehicleFeature, int id) {
        vehicleFeatureDAO.update(vehicleFeature, id);
    }

    @Override
    public void deleteRow(int id) {
        vehicleFeatureDAO.delete(id);
    }
}
