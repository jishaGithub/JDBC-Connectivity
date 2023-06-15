package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLInsuranceDAO;
import com.solvd.laba.db.model.Insurance;
import com.solvd.laba.db.service.DAOService;

public class InsuranceService implements DAOService<Insurance> {
    private MySQLInsuranceDAO insuranceDAO;

    public InsuranceService(MySQLInsuranceDAO insuranceDAO) {
        this.insuranceDAO = insuranceDAO;
    }

    @Override
    public void findById(int id) {
        insuranceDAO.get(id);
    }

    @Override
    public void selectAll() {
        insuranceDAO.get();
    }

    @Override
    public void newRow(Insurance insurance) {
        insuranceDAO.add(insurance);
    }

    @Override
    public void updateRow(Insurance insurance, int id) {
        insuranceDAO.update(insurance, id);
    }

    @Override
    public void deleteRow(int id) {
        insuranceDAO.delete(id);
    }
}
