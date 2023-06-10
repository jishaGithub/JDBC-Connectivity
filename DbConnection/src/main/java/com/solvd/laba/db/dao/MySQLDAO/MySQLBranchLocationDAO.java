package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.BranchLocation;
import java.sql.*;
import java.util.ArrayList;

public class MySQLBranchLocationDAO extends AbstractDAO<BranchLocation> {
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM branch_location WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int branchLocationId = result.getInt("id");
                String street = result.getString("street");
                String city = result.getString("city");
                String state = result.getString("state");
                String zip = result.getString("zip");
                BranchLocation branchLocation = new BranchLocation(branchLocationId, street, city, state, zip);
                System.out.println(branchLocation);
                System.out.println();
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("SQL query error");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<BranchLocation> selectAll() {
        System.out.println("Displaying all rows from branch_location table...");
        ArrayList<BranchLocation> branchLocations = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM branch_location")) {
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
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        System.out.println(branchLocations);
        return branchLocations;
    }

    @Override
    public void addNewRow(BranchLocation row) {
        String insertValueStatement = "INSERT INTO branch_location (street, city, state, zip) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setString(1, row.getStreet());
            preparedStatement.setString(2, row.getCity());
            preparedStatement.setString(3, row.getState());
            preparedStatement.setString(4, row.getZip());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(BranchLocation branchLocation, int id) {
       String updateStatement = "UPDATE branch_location SET street=?, city=?, state=?, zip=? WHERE id=?";
       try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            preparedStatement.setString(1, branchLocation.getStreet());
            preparedStatement.setString(2, branchLocation.getCity());
            preparedStatement.setString(3, branchLocation.getState());
            preparedStatement.setString(4, branchLocation.getZip());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM branch_location WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteStatement)) {
            if (findById(id)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
                System.out.println(selectAll());
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }
}
