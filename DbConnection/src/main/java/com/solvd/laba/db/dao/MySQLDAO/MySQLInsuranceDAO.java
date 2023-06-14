package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Insurance;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class MySQLInsuranceDAO extends AbstractDAO<Insurance> {
    private static final Logger logger = LogManager.getLogger(MySQLInsuranceDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM insurance WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM insurance";
    private final static String INSERT_VALUES = "INSERT INTO insurance (insurance_name, coverage, customer_id) VALUES (?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE insurance SET insurance_name=?, coverage=?, customer_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM insurance WHERE id=?";

    @Override
    public Insurance get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int insuranceId = result.getInt("id");
                String insuranceName = result.getString("insurance_name");
                double coverage = result.getDouble("coverage");
                int customerId = result.getInt("customer_id");
                return new Insurance(insuranceId, insuranceName, coverage, customerId);
            } else {
                logger.info("No record found. Invalid ID");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Insurance> get() {
        logger.info("Displaying all the rows from insurance table");
        ArrayList<Insurance> insurances = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int insuranceId = result.getInt("id");
                String insuranceName = result.getString("insurance_name");
                double coverage = result.getDouble("coverage");
                int customerId = result.getInt("customer_id");
                Insurance insurance = new Insurance(insuranceId, insuranceName, coverage, customerId);
                insurances.add(insurance);
            }
            logger.info(insurances);
            return insurances;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Insurance insurance) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, insurance.getInsuranceName());
            preparedStatement.setDouble(2, insurance.getCoverage());
            preparedStatement.setInt(3, insurance.getCustomerId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Insurance insurance, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, insurance.getInsuranceName());
            preparedStatement.setDouble(2, insurance.getCoverage());
            preparedStatement.setInt(3, insurance.getCustomerId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            if (get(id) != null) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                logger.info("Deletion complete");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

}
