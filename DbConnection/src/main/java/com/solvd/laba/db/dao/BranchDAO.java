package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.Branch;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class BranchDAO extends AbstractDAO implements GenericDAO<Branch> {
    private static final Logger logger = LogManager.getLogger(BranchDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM branch WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM branch";
    private final static String INSERT_VALUES = "INSERT INTO branch (branch_name, branch_location_id) VALUES (?, ?)";
    private final static String UPDATE_VALUE = "UPDATE branch SET branch_name=?, branch_location_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM branch WHERE id=?";

    @Override
    public Branch get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int branchId = result.getInt("id");
                String branchName = result.getString("branch_name");
                int branchLocationId = result.getInt("branch_location_id");
                Branch branch = new Branch(branchId, branchName, branchLocationId);
                logger.info(branch);
                return branch;
            }
            logger.info("No record found. Invalid ID");
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Branch> get() {
        logger.info("Displaying all the rows from the branch table...");
        ArrayList<Branch> branches = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int branchId = result.getInt("id");
                String branchName = result.getString("branch_name");
                int branchLocationId = result.getInt("branch_location_id");
                Branch branch = new Branch(branchId, branchName, branchLocationId);
                branches.add(branch);
            }
            logger.info(branches);
            return branches;
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Branch row) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, row.getBranchName());
            preparedStatement.setInt(2, row.getBranchLocationId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
    }

    @Override
    public void update(Branch branch, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, branch.getBranchName());
            preparedStatement.setInt(2, branch.getBranchLocationId());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.error("Update error:"+e.getMessage());
            logger.error("Error! id is null. Use another non-null id");
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.info("Deletion complete");
        } catch (SQLException e) {
            logger.error("Deletion unsuccessful!"+e.getMessage());
        }
    }
}
