package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.RentalRateDAO;
import com.solvd.laba.db.model.RentalRate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class RentalRateService implements GenericDAO<RentalRate> {    
    private RentalRateDAO rentalRateDAO;

    public RentalRateService(RentalRateDAO rentalRateDAO) {
        this.rentalRateDAO = rentalRateDAO;
    }

    @Override
    public RentalRate get(int id) {
        return rentalRateDAO.get(id);
    }

    @Override
    public ArrayList<RentalRate> get() {
        return rentalRateDAO.get();
    }

    @Override
    public void add(RentalRate rentalRate) {
        rentalRateDAO.add(rentalRate);
    }

    @Override
    public void update(RentalRate rentalRate, int id) {
        rentalRateDAO.update(rentalRate, id);
    }

    @Override
    public void delete(int id) {
        rentalRateDAO.delete(id);
    }
}
