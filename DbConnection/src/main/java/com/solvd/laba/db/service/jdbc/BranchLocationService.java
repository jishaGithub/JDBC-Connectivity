package com.solvd.laba.db.service.jdbc;

import com.solvd.laba.db.dao.BranchLocationDAO;
import com.solvd.laba.db.util.GenericDAO;
import com.solvd.laba.db.model.BranchLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;

public class BranchLocationService implements GenericDAO<BranchLocation> {
    private BranchLocationDAO branchLocationDAO;

    public BranchLocationService(BranchLocationDAO branchLocationDAO) {
        this.branchLocationDAO = branchLocationDAO;
    }

    @Override
    public BranchLocation get(int id) {
        return branchLocationDAO.get(id);
    }

    @Override
    public ArrayList<BranchLocation> get() {
        return branchLocationDAO.get();
    }

    @Override
    public void add(BranchLocation branchLocation) {
        branchLocationDAO.add(branchLocation);
    }

    @Override
    public void update(BranchLocation branchLocation, int id) {
        branchLocationDAO.update(branchLocation, id);
    }

    @Override
    public void delete(int id) {
        branchLocationDAO.delete(id);
    }
}
