package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.VehicleCategory;
import java.sql.*;
import java.util.ArrayList;

public class MySQLVehicleCategoryDAO extends AbstractDAO<VehicleCategory> {

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM vehicle_category WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("category_name");
                VehicleCategory vehicleCategory = new VehicleCategory(categoryId, categoryName);
                System.out.println(vehicleCategory);
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                return false;
            }
        } catch (SQLException se) {
            System.out.println("Error executing SQL query");
            se.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<VehicleCategory> selectAll() {
        System.out.println("Displaying all the rows from vehicle_category table");
        ArrayList<VehicleCategory> vehicleCategories = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM vehicle_category")) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("category_name");
                VehicleCategory vehicleCategory = new VehicleCategory(categoryId, categoryName);
                vehicleCategories.add(vehicleCategory);
            }
            System.out.println(vehicleCategories);
            return vehicleCategories;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(VehicleCategory vehicleCategory) {
        String insertValueStatement = "INSERT INTO vehicle_category(category_name) VALUES (?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setString(1, vehicleCategory.getCategoryName());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleCategory vehicleCategory, int id) {
        String updateStatement = "UPDATE vehicle_category SET category_name=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            preparedStatement.setString(1, vehicleCategory.getCategoryName());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM vehicle_category WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteStatement)) {
            if (findById(id)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
            }
        } catch (SQLException e) {
            System.out.println("Deletion unsuccessful!");
            e.printStackTrace();
        }
    }

}
