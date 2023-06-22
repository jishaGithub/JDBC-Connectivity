package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.VehicleCategoryDAO;
import com.solvd.laba.db.model.VehicleCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class VehicleCategoryService implements GenericDAO<VehicleCategory> {    
    private VehicleCategoryDAO vehicleCategoryDAO;

    public VehicleCategoryService(VehicleCategoryDAO vehicleCategoryDAO) {
        this.vehicleCategoryDAO = vehicleCategoryDAO;
    }

    @Override
    public VehicleCategory get(int id) {
        return vehicleCategoryDAO.get(id);
    }

    @Override
    public ArrayList<VehicleCategory> get() {
        return vehicleCategoryDAO.get();
    }

    @Override
    public void add(VehicleCategory vehicleCategory) {
        vehicleCategoryDAO.add(vehicleCategory);
    }

    @Override
    public void update(VehicleCategory vehicleCategory, int id) {
        vehicleCategoryDAO.update(vehicleCategory, id);
    }

    @Override
    public void delete(int id) {
        vehicleCategoryDAO.delete(id);
    }
}
