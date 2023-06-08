package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.VehicleHasVehicleFeature;
import java.sql.*;
import java.util.ArrayList;

public class MySQLVehicleHasVehicleFeatureDAO extends AbstractDAO<VehicleHasVehicleFeature> {
    public MySQLVehicleHasVehicleFeatureDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int vehicleId) {
        System.out.println("Finding record with vehicleId: " + vehicleId + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle_has_vehicle_feature WHERE vehicle_id = ?");
            preparedStatement.setInt(1, vehicleId);
            ResultSet result = preparedStatement.executeQuery();
            boolean recordExists = result.next();
            System.out.println("------------------------------------------------");
            return recordExists;
        } catch (SQLException se) {
            System.out.println("Error finding record. Invalid vehicleId");
            System.out.println("------------------------------------------------");
            return false;
        }
    }

    @Override
    public ArrayList<VehicleHasVehicleFeature> selectAll() {
        System.out.println("Displaying all rows from vehicle_has_vehicle_feature table");
        ArrayList<VehicleHasVehicleFeature> records = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle_has_vehicle_feature");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int vehicleId = result.getInt("vehicle_id");
                int vehicleFeatureId = result.getInt("vehicle_feature_id");
                VehicleHasVehicleFeature record = new VehicleHasVehicleFeature(vehicleId, vehicleFeatureId);
                records.add(record);
            }
            System.out.println(records);
            return records;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(VehicleHasVehicleFeature row) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO vehicle_has_vehicle_feature(vehicle_id, vehicle_feature_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setInt(1, row.getVehicleId());
            preparedStatement.setInt(2, row.getVehicleFeatureId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleHasVehicleFeature recordToUpdate, int vehicleId) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE vehicle_has_vehicle_feature SET vehicle_id = ?, vehicle_feature_id = ? WHERE vehicle_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setInt(1, recordToUpdate.getVehicleId());
            preparedStatement.setInt(2, recordToUpdate.getVehicleFeatureId());
            preparedStatement.setInt(3, vehicleId);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int vehicleId) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(vehicleId)) {
                String deleteStatement = "DELETE FROM vehicle_has_vehicle_feature WHERE vehicle_id = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setInt(1, vehicleId);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
            }
        } catch (SQLException e) {
            System.out.println("Deletion unsuccessful!");
            e.printStackTrace();
        }
    }

}
