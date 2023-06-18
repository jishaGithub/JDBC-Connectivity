package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.InsuranceDAO;
import com.solvd.laba.db.model.Insurance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class InsuranceService implements GenericDAO<Insurance> {
    private static final Logger logger = LogManager.getLogger(InsuranceService.class);
    private InsuranceDAO insuranceDAO;

    public InsuranceService(InsuranceDAO insuranceDAO) {
        this.insuranceDAO = insuranceDAO;
    }

    @Override
    public Insurance get(int id) {
        return insuranceDAO.get(id);
    }

    @Override
    public ArrayList<Insurance> get() {
        return insuranceDAO.get();
    }

    @Override
    public void add(Insurance insurance) {
        insuranceDAO.add(insurance);
    }

    @Override
    public void update(Insurance insurance, int id) {
        insuranceDAO.update(insurance, id);
    }

    @Override
    public void delete(int id) {
        insuranceDAO.delete(id);
    }
}
