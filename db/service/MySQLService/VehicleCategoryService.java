package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLVehicleCategoryDAO;
import com.solvd.laba.db.model.VehicleCategory;
import com.solvd.laba.db.service.DAOService;

public class VehicleCategoryService implements DAOService<VehicleCategory> {
    private MySQLVehicleCategoryDAO vehicleCategoryDAO;

    public VehicleCategoryService(MySQLVehicleCategoryDAO vehicleCategoryDAO) {
        this.vehicleCategoryDAO = vehicleCategoryDAO;
    }

    @Override
    public void findById(int id) {
        vehicleCategoryDAO.findById(id);
    }

    @Override
    public void selectAll() {
        vehicleCategoryDAO.selectAll();
    }

    @Override
    public void newRow(VehicleCategory vehicleCategory) {
        vehicleCategoryDAO.addNewRow(vehicleCategory);
    }

    @Override
    public void updateRow(VehicleCategory vehicleCategory, int id) {
        vehicleCategoryDAO.update(vehicleCategory, id);
    }

    @Override
    public void deleteRow(int id) {
        vehicleCategoryDAO.delete(id);
    }
}
