package com.solvd.laba.db.service.jdbc;

import com.solvd.laba.db.dao.VehicleHasVehicleFeatureDAO;
import com.solvd.laba.db.model.VehicleHasVehicleFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class VehicleHasVehicleFeatureService implements GenericDAO<VehicleHasVehicleFeature> {
    private static final Logger logger = LogManager.getLogger(VehicleHasVehicleFeatureService.class);
    private VehicleHasVehicleFeatureDAO vehicleHasVehicleFeatureDAO;

    public VehicleHasVehicleFeatureService(VehicleHasVehicleFeatureDAO vehicleHasVehicleFeatureDAO) {
        this.vehicleHasVehicleFeatureDAO = vehicleHasVehicleFeatureDAO;
    }

    @Override
    public VehicleHasVehicleFeature get(int id) {
        return vehicleHasVehicleFeatureDAO.get(id);
    }

    @Override
    public ArrayList<VehicleHasVehicleFeature> get() {
        return vehicleHasVehicleFeatureDAO.get();
    }

    @Override
    public void add(VehicleHasVehicleFeature vehicleHasVehicleFeature) {
        vehicleHasVehicleFeatureDAO.add(vehicleHasVehicleFeature);
    }

    @Override
    public void update(VehicleHasVehicleFeature vehicleHasVehicleFeature, int id) {
        vehicleHasVehicleFeatureDAO.update(vehicleHasVehicleFeature, id);
    }

    @Override
    public void delete(int id) {
        vehicleHasVehicleFeatureDAO.delete(id);
    }
}
