package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLBranchLocationDAO;
import com.solvd.laba.db.model.BranchLocation;
import com.solvd.laba.db.service.DAOService;

public class BranchLocationService implements DAOService<BranchLocation> {
    private MySQLBranchLocationDAO branchLocationDAO;

    public BranchLocationService(MySQLBranchLocationDAO branchLocationDAO) {
        this.branchLocationDAO = branchLocationDAO;
    }

    @Override
    public void findById(int id) {
        branchLocationDAO.get(id);
    }

    @Override
    public void selectAll() {
        branchLocationDAO.get();
    }

    @Override
    public void newRow(BranchLocation branchLocation) {
        branchLocationDAO.add(branchLocation);
    }

    @Override
    public void updateRow(BranchLocation branchLocation, int id) {
        branchLocationDAO.update(branchLocation, id);
    }

    @Override
    public void deleteRow(int id) {
        branchLocationDAO.delete(id);
    }
}
