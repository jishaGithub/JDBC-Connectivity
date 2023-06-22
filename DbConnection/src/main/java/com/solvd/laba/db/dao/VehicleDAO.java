package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.Vehicle;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAO extends AbstractDAO implements GenericDAO<Vehicle> {
    private static final Logger logger = LogManager.getLogger(VehicleDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM vehicle WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM vehicle";
    private final static String INSERT_VALUES = "INSERT INTO vehicle (brand, model, year, color, mileage, license_num, category_id, customer_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE vehicle SET brand=?, model=?, year=?, color=?, mileage=?, license_num=?, category_id=?, customer_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM vehicle WHERE id=?";

    @Override
    public Vehicle get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int vehicleId = result.getInt("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                String color = result.getString("color");
                int mileage = result.getInt("mileage");
                String licenseNumber = result.getString("license_num");
                int categoryId = result.getInt("category_id");
                int customerId = result.getInt("customer_id");
                Vehicle vehicle = new Vehicle(vehicleId, brand, model, year, color, mileage, licenseNumber, categoryId, customerId);
                logger.info(vehicle);
                return vehicle;
            }
            logger.info("No record found. Invalid ID");
        } catch (SQLException e) {
            logger.error("Error executing SQL query");
        }
        return null;
    }

    @Override
    public ArrayList<Vehicle> get() {
        logger.info("Displaying all the rows from vehicle table");
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int vehicleId = result.getInt("id");
                String brand = result.getString("brand");
                String model = result.getString("model");
                int year = result.getInt("year");
                String color = result.getString("color");
                int mileage = result.getInt("mileage");
                String licenseNumber = result.getString("license_num");
                int categoryId = result.getInt("category_id");
                int customerId = result.getInt("customer_id");
                Vehicle vehicle = new Vehicle(vehicleId, brand, model, year, color, mileage, licenseNumber, categoryId, customerId);
                vehicles.add(vehicle);
            }
            logger.info(vehicles);
            return vehicles;
        } catch (SQLException e) {
            logger.error("Error executing SQL query");
        }
        return null;
    }

    @Override
    public void add(Vehicle vehicle) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, vehicle.getBrand());
            preparedStatement.setString(2, vehicle.getModel());
            preparedStatement.setInt(3, vehicle.getYear());
            preparedStatement.setString(4, vehicle.getColor());
            preparedStatement.setInt(5, vehicle.getMileage());
            preparedStatement.setString(6, vehicle.getLicenseNum());
            preparedStatement.setInt(7, vehicle.getCategoryId());
            preparedStatement.setInt(8, vehicle.getCustomerId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.error("Error executing SQL query");
        }
    }

    @Override
    public void update(Vehicle vehicle, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
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
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.error("Update error:"+e.getMessage());
            logger.error("Error! id is null. Use another non-null id");
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
            logger.error("Error executing SQL query");
        }
    }
}
