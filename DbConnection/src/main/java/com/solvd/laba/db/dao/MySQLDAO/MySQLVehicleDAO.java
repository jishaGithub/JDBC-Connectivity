package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Vehicle;
import java.sql.*;
import java.util.ArrayList;

public class MySQLVehicleDAO extends AbstractDAO<Vehicle> {
    public MySQLVehicleDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id1) {
        System.out.println("Finding record of ID:" + id1 + ".....");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle where id=?");
            preparedStatement.setInt(1, id1);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int id = result.getInt("id");
            String brand = result.getString("brand");
            String model = result.getString("model");
            int year = result.getInt("year");
            String color = result.getString("color");
            int mileage = result.getInt("mileage");
            String licenseNumber = result.getString("license_num");
            int categoryId = result.getInt("category_id");
            int customerId = result.getInt("customer_id");
            Vehicle vehicle = new Vehicle(id, brand, model, year, color, mileage, licenseNumber,categoryId, customerId);
            System.out.println(vehicle);
            System.out.println(" ");
            return true;
        } catch (SQLException se) {
            System.out.println("No record found. Invalid ID");
            System.out.println("  ");
            return false;
        }
    }

    @Override
    public ArrayList<Vehicle> selectAll() {
        System.out.println("Displaying all the rows from vehicle table");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM vehicle");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                String color = result.getString("color");
                int mileage = result.getInt("mileage");
                String licenseNumber = result.getString("license_num");
                int categoryId = result.getInt("category_id");
                int customerId = result.getInt("customer_id");
                Vehicle vehicle = new Vehicle(id, brand, model, year, color, mileage, licenseNumber,categoryId, customerId);
                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Vehicle row) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO `vehicle`(`brand`,`model`,`year`,`color`,mileage,`license_num`,`category_id`,`customer_id`)\n" +
                    "VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, row.getBrand());
            preparedStatement.setString(2, row.getModel());
            preparedStatement.setInt(3, row.getYear());
            preparedStatement.setString(4, row.getColor());
            preparedStatement.setInt(5, row.getMileage());
            preparedStatement.setString(6,row.getLicenseNum());
            preparedStatement.setInt(7,row.getCategoryId());
            preparedStatement.setInt(8,row.getCustomerId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Vehicle vehicle, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE vehicle SET brand=?,model=?,year=?,color=?,mileage=?," +
                    "license_num=?,category_id=?,customer_id=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getColor());
            preparedStatement.setInt(5, vehicle.getMileage());
            preparedStatement.setString(6, vehicle.getLicenseNum());
            preparedStatement.setInt(7, vehicle.getCategoryId());
            preparedStatement.setInt(8, vehicle.getCustomerId());
            preparedStatement.setInt(9, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String deleteStatement = "DELETE from vehicle WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
                System.out.println(selectAll());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
