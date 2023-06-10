package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLRentalDAO;
import com.solvd.laba.db.model.Rental;
import com.solvd.laba.db.service.DAOService;

public class RentalService implements DAOService<Rental> {
    private MySQLRentalDAO rentalDAO;

    public RentalService(MySQLRentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    @Override
    public void findById(int id) {
        rentalDAO.findById(id);
    }

    @Override
    public void selectAll() {
        rentalDAO.selectAll();
    }

    @Override
    public void newRow(Rental rental) {
        rentalDAO.addNewRow(rental);
    }

    @Override
    public void updateRow(Rental rental, int id) {
        rentalDAO.update(rental, id);
    }

    @Override
    public void deleteRow(int id) {
        rentalDAO.delete(id);
    }
}
