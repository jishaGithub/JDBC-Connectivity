package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.VehicleFeatureDAO;
import com.solvd.laba.db.model.VehicleFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class VehicleFeatureService implements GenericDAO<VehicleFeature> {    
    private VehicleFeatureDAO vehicleFeatureDAO;

    public VehicleFeatureService(VehicleFeatureDAO vehicleFeatureDAO) {
        this.vehicleFeatureDAO = vehicleFeatureDAO;
    }

    @Override
    public VehicleFeature get(int id) {
        return vehicleFeatureDAO.get(id);
    }

    @Override
    public ArrayList<VehicleFeature> get() {
        return vehicleFeatureDAO.get();
    }

    @Override
    public void add(VehicleFeature vehicleFeature) {
        vehicleFeatureDAO.add(vehicleFeature);
    }

    @Override
    public void update(VehicleFeature vehicleFeature, int id) {
        vehicleFeatureDAO.update(vehicleFeature, id);
    }

    @Override
    public void delete(int id) {
        vehicleFeatureDAO.delete(id);
    }
}
