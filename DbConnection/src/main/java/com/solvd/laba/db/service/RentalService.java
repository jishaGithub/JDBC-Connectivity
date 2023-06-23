package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.RentalDAO;
import com.solvd.laba.db.model.Rental;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class RentalService implements GenericDAO<Rental> {    
    private RentalDAO rentalDAO;

    public RentalService(RentalDAO rentalDAO) {
        this.rentalDAO = rentalDAO;
    }

    @Override
    public Rental get(int id) {
        return rentalDAO.get(id);
    }

    @Override
    public ArrayList<Rental> get() {
        return rentalDAO.get();
    }

    @Override
    public void add(Rental rental) {
        rentalDAO.add(rental);
    }

    @Override
    public void update(Rental rental, int id) {
        rentalDAO.update(rental, id);
    }

    @Override
    public void delete(int id) {
        rentalDAO.delete(id);
    }
}
