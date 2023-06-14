package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.VehicleCategory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MySQLVehicleCategoryDAO extends AbstractDAO<VehicleCategory> {
    private static final Logger logger = LogManager.getLogger(MySQLVehicleCategoryDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM vehicle_category WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM vehicle_category";
    private final static String INSERT_VALUE = "INSERT INTO vehicle_category (category_name) VALUES (?)";
    private final static String UPDATE_VALUE = "UPDATE vehicle_category SET category_name=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM vehicle_category WHERE id=?";

    @Override
    public VehicleCategory get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("category_name");
                return new VehicleCategory(categoryId, categoryName);
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
    public ArrayList<VehicleCategory> get() {
        logger.info("Displaying all the rows from vehicle_category table");
        ArrayList<VehicleCategory> vehicleCategories = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int categoryId = result.getInt("id");
                String categoryName = result.getString("category_name");
                VehicleCategory vehicleCategory = new VehicleCategory(categoryId, categoryName);
                vehicleCategories.add(vehicleCategory);
            }
            logger.info(vehicleCategories);
            return vehicleCategories;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(VehicleCategory vehicleCategory) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUE)) {
            preparedStatement.setString(1, vehicleCategory.getCategoryName());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(VehicleCategory vehicleCategory, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, vehicleCategory.getCategoryName());
            preparedStatement.setInt(2, id);
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
