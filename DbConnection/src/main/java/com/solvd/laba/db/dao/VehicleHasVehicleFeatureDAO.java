package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.VehicleHasVehicleFeature;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleHasVehicleFeatureDAO extends AbstractDAO implements GenericDAO<VehicleHasVehicleFeature> {
    private static final Logger logger = LogManager.getLogger(VehicleHasVehicleFeatureDAO.class);
    private static final String FIND_BY_ID = "SELECT * FROM vehicle_has_vehicle_feature WHERE vehicle_id=?";
    private static final String SELECT_ALL = "SELECT * FROM vehicle_has_vehicle_feature";
    private static final String INSERT_VALUES = "INSERT INTO vehicle_has_vehicle_feature (vehicle_id, vehicle_feature_id) VALUES (?, ?)";
    private static final String UPDATE_VALUE = "UPDATE vehicle_has_vehicle_feature SET vehicle_id=?, vehicle_feature_id=? WHERE vehicle_id=?";
    private static final String DELETE_VALUE = "DELETE FROM vehicle_has_vehicle_feature WHERE vehicle_id=?";

    @Override
    public VehicleHasVehicleFeature get(int vehicleId) {
        logger.info("Finding record with vehicleId: " + vehicleId + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, vehicleId);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int foundVehicleId = result.getInt("vehicle_id");
                int vehicleFeatureId = result.getInt("vehicle_feature_id");
                VehicleHasVehicleFeature vehicleHasVehicleFeature = new VehicleHasVehicleFeature(foundVehicleId, vehicleFeatureId);
                logger.info(vehicleHasVehicleFeature);
                return vehicleHasVehicleFeature;
            }
            logger.info("No record found. Invalid vehicleId");
        } catch (SQLException e) {
            logger.info("Error finding record. Invalid vehicleId:" + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<VehicleHasVehicleFeature> get() {
        logger.info("Displaying all rows from vehicle_has_vehicle_feature table");
        ArrayList<VehicleHasVehicleFeature> records = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(SELECT_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int vehicleId = result.getInt("vehicle_id");
                int vehicleFeatureId = result.getInt("vehicle_feature_id");
                VehicleHasVehicleFeature record = new VehicleHasVehicleFeature(vehicleId, vehicleFeatureId);
                records.add(record);
            }
            logger.info(records);
            return records;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(VehicleHasVehicleFeature record) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setInt(1, record.getVehicleId());
            preparedStatement.setInt(2, record.getVehicleFeatureId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleHasVehicleFeature record, int vehicleId) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setInt(1, record.getVehicleId());
            preparedStatement.setInt(2, record.getVehicleFeatureId());
            preparedStatement.setInt(3, vehicleId);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.info("Update error:"+e.getMessage());
            logger.info("Error! id is null. Use another non-null id");
        }
    }

    @Override
    public void delete(int vehicleId) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            if (get(vehicleId) != null) {
                preparedStatement.setInt(1, vehicleId);
                preparedStatement.executeUpdate();
                logger.info("Deletion complete");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }
}
