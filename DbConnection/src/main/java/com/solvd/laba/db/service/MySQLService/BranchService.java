package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLBranchDAO;
import com.solvd.laba.db.dao.MySQLDAO.MySQLBranchLocationDAO;
import com.solvd.laba.db.model.Branch;
import com.solvd.laba.db.service.DAOService;

public class BranchService implements DAOService<Branch> {
    private MySQLBranchDAO branchDAO;
    private MySQLBranchLocationDAO branchLocationDAO;

    public BranchService(MySQLBranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    @Override
    public void findById(int id) {
        branchDAO.get(id);
    }

    @Override
    public void selectAll() {
        branchDAO.get();
    }

    @Override
    public void newRow(Branch branch) {
        branchDAO.add(branch);
    }

    @Override
    public void updateRow(Branch branch, int id) {
        branchDAO.update(branch, id);
    }

    @Override
    public void deleteRow(int id) {
        branchDAO.delete(id);
    }
}
