package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLVehicleDAO;
import com.solvd.laba.db.model.Vehicle;
import com.solvd.laba.db.service.DAOService;

public class VehicleService implements DAOService<Vehicle> {
    private MySQLVehicleDAO vehicleDAO;

    public VehicleService(MySQLVehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }

    @Override
    public void findById(int id) {
        vehicleDAO.findById(id);
    }

    @Override
    public void selectAll() {
        vehicleDAO.selectAll();
    }

    @Override
    public void newRow(Vehicle vehicle) {
        vehicleDAO.addNewRow(vehicle);
    }

    @Override
    public void updateRow(Vehicle vehicle, int id) {
        vehicleDAO.update(vehicle, id);
    }

    @Override
    public void deleteRow(int id) {
        vehicleDAO.delete(id);
    }
}