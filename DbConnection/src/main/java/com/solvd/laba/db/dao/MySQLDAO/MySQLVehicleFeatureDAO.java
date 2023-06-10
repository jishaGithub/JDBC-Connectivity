package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.VehicleFeature;
import java.sql.*;
import java.util.ArrayList;

public class MySQLVehicleFeatureDAO extends AbstractDAO<VehicleFeature> {

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record with ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM vehicle_feature WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int featureId = result.getInt("id");
                String name = result.getString("name");
                String availability = result.getString("availability");
                double additionalCost = result.getDouble("additional_cost");
                VehicleFeature vehicleFeature = new VehicleFeature(featureId, name, availability, additionalCost);
                System.out.println(vehicleFeature);
                System.out.println(" ");
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                System.out.println(" ");
                return false;
            }
        } catch (SQLException se) {
            System.out.println("Error executing SQL statement");
            se.printStackTrace();
            return false;
        }
    }

    @Override
    public ArrayList<VehicleFeature> selectAll() {
        System.out.println("Displaying all rows from vehicle_feature table...");
        ArrayList<VehicleFeature> vehicleFeatures = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM vehicle_feature")) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                String availability = result.getString("availability");
                double additionalCost = result.getDouble("additional_cost");
                VehicleFeature vehicleFeature = new VehicleFeature(id, name, availability, additionalCost);
                vehicleFeatures.add(vehicleFeature);
            }
            System.out.println(vehicleFeatures);
            return vehicleFeatures;
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addNewRow(VehicleFeature vehicleFeature) {
        String insertValueStatement = "INSERT INTO vehicle_feature(name, availability, additional_cost) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setString(1, vehicleFeature.getName());
            preparedStatement.setString(2, vehicleFeature.getAvailability());
            preparedStatement.setDouble(3, vehicleFeature.getAdditionalCost());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleFeature vehicleFeature, int id) {
        String updateStatement = "UPDATE vehicle_feature SET name=?, availability=?, additional_cost=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            preparedStatement.setString(1, vehicleFeature.getName());
            preparedStatement.setString(2, vehicleFeature.getAvailability());
            preparedStatement.setDouble(3, vehicleFeature.getAdditionalCost());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM vehicle_feature WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteStatement)) {
            if (findById(id)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

}
