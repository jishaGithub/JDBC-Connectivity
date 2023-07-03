package com.solvd.laba.db.service.jdbc;

import com.solvd.laba.db.dao.VehicleDAO;
import com.solvd.laba.db.model.Vehicle;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class VehicleService implements GenericDAO<Vehicle> {
    private static final Logger logger = LogManager.getLogger(VehicleService.class);
    private VehicleDAO vehicleDAO;

    public VehicleService(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public Vehicle get(int id) {
        return vehicleDAO.get(id);
    }

    @Override
    public ArrayList<Vehicle> get() {
        return vehicleDAO.get();
    }

    @Override
    public void add(Vehicle vehicle) {
        vehicleDAO.add(vehicle);
    }

    @Override
    public void update(Vehicle vehicle, int id) {
        vehicleDAO.update(vehicle, id);
    }

    @Override
    public void delete(int id) {
        vehicleDAO.delete(id);
    }
}
