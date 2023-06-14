package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.BranchLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;

public class MySQLBranchLocationDAO extends AbstractDAO<BranchLocation> {
    private static final Logger logger = LogManager.getLogger(MySQLBranchLocationDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM branch_location WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM branch_location";
    private final static String INSERT_VALUES = "INSERT INTO branch_location (street, city, state, zip) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE branch_location SET street=?, city=?, state=?, zip=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM branch_location WHERE id=?";

    @Override
    public BranchLocation get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int branchLocationId = result.getInt("id");
                String street = result.getString("street");
                String city = result.getString("city");
                String state = result.getString("state");
                String zip = result.getString("zip");
                BranchLocation branchLocation = new BranchLocation(branchLocationId, street, city, state, zip);
                logger.info(branchLocation);
                return branchLocation;
            } else {
                logger.info("No record found. Invalid ID");
            }
        } catch (SQLException e) {
            logger.info("SQL query error:"+e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<BranchLocation> get() {
        System.out.println("Displaying all rows from branch_location table...");
        ArrayList<BranchLocation> branchLocations = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int branchLocationId = result.getInt("id");
                String street = result.getString("street");
                String city = result.getString("city");
                String state = result.getString("state");
                String zip = result.getString("zip");
                BranchLocation branchLocation = new BranchLocation(branchLocationId, street, city, state, zip);
                branchLocations.add(branchLocation);
            }
            logger.info(branchLocations);
            return branchLocations;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(BranchLocation branchLocation) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, branchLocation.getStreet());
            preparedStatement.setString(2, branchLocation.getCity());
            preparedStatement.setString(3, branchLocation.getState());
            preparedStatement.setString(4, branchLocation.getZip());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:"+e.getMessage());
        }
    }

    @Override
    public void update(BranchLocation branchLocation, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, branchLocation.getStreet());
            preparedStatement.setString(2, branchLocation.getCity());
            preparedStatement.setString(3, branchLocation.getState());
            preparedStatement.setString(4, branchLocation.getZip());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:"+e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            System.out.println("Deletion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:"+e.getMessage());
        }
    }
}
