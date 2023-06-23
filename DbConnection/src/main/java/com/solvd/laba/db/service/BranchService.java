package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.BranchDAO;
import com.solvd.laba.db.model.Branch;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import com.solvd.laba.db.util.GenericDAO;

public class BranchService implements GenericDAO<Branch> {
    private BranchDAO branchDAO;

    public BranchService(BranchDAO branchDAO) {
        this.branchDAO = branchDAO;
    }

    @Override
    public Branch get(int id) {
        return branchDAO.get(id);
    }

    @Override
    public ArrayList<Branch> get() {
        return branchDAO.get();
    }

    @Override
    public void add(Branch branch) {
        branchDAO.add(branch);
    }

    @Override
    public void update(Branch branch, int id) {
        branchDAO.update(branch, id);
    }

    @Override
    public void delete(int id) {
        branchDAO.delete(id);
    }
}
