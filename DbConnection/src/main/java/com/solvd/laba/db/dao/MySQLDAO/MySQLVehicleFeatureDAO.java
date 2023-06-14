package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.VehicleFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLVehicleFeatureDAO extends AbstractDAO<VehicleFeature> {
    private static final Logger logger = LogManager.getLogger(MySQLVehicleFeatureDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM vehicle_feature WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM vehicle_feature";
    private final static String INSERT_VALUES = "INSERT INTO vehicle_feature (name, availability, additional_cost) VALUES (?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE vehicle_feature SET name=?, availability=?, additional_cost=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM vehicle_feature WHERE id=?";

    @Override
    public VehicleFeature get(int id) {
        logger.info("Finding record with ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int featureId = result.getInt("id");
                String name = result.getString("name");
                String availability = result.getString("availability");
                double additionalCost = result.getDouble("additional_cost");
                return new VehicleFeature(featureId, name, availability, additionalCost);
            } else {
                logger.info("No record found. Invalid ID");
            }
        } catch (SQLException se) {
            logger.info("Error executing SQL query");
            se.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<VehicleFeature> get() {
        logger.info("Displaying all rows from vehicle_feature table...");
        ArrayList<VehicleFeature> vehicleFeatures = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String availability = result.getString("availability");
                double additionalCost = result.getDouble("additional_cost");
                VehicleFeature vehicleFeature = new VehicleFeature(id, name, availability, additionalCost);
                vehicleFeatures.add(vehicleFeature);
            }
            return vehicleFeatures;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(VehicleFeature vehicleFeature) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, vehicleFeature.getName());
            preparedStatement.setString(2, vehicleFeature.getAvailability());
            preparedStatement.setDouble(3, vehicleFeature.getAdditionalCost());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleFeature vehicleFeature, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, vehicleFeature.getName());
            preparedStatement.setString(2, vehicleFeature.getAvailability());
            preparedStatement.setDouble(3, vehicleFeature.getAdditionalCost());
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
