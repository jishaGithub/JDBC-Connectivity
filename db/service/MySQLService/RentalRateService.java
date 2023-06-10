package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLRentalRateDAO;
import com.solvd.laba.db.model.RentalRate;
import com.solvd.laba.db.service.DAOService;

public class RentalRateService implements DAOService<RentalRate> {
    private MySQLRentalRateDAO rentalRateDAO;

    public RentalRateService(MySQLRentalRateDAO rentalRateDAO) {
        this.rentalRateDAO = rentalRateDAO;
    }

    @Override
    public void findById(int id) {
        rentalRateDAO.findById(id);
    }

    @Override
    public void selectAll() {
        rentalRateDAO.selectAll();
    }

    @Override
    public void newRow(RentalRate rentalRate) {
        rentalRateDAO.addNewRow(rentalRate);
    }

    @Override
    public void updateRow(RentalRate rentalRate, int id) {
        rentalRateDAO.update(rentalRate, id);
    }

    @Override
    public void deleteRow(int id) {
        rentalRateDAO.delete(id);
    }
}
