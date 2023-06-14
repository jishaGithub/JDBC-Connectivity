package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Rental;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class MySQLRentalDAO extends AbstractDAO<Rental> {
    private static final Logger logger = LogManager.getLogger(MySQLRentalDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM rental WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM rental";
    private final static String INSERT_VALUES = "INSERT INTO rental (rental_date, return_date, customer_id, insurance_id, rental_rate_id, vehicle_id, promotion_id, employee_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE rental SET rental_date=?, return_date=?, customer_id=?, insurance_id=?, rental_rate_id=?, vehicle_id=?, promotion_id=?, employee_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM rental WHERE id=?";

    @Override
    public Rental get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int rentalId = result.getInt("id");
                Date rentalDate = result.getDate("rental_date");
                Date returnDate = result.getDate("return_date");
                int customerId = result.getInt("customer_id");
                int insuranceId = result.getInt("insurance_id");
                int rentalRateId = result.getInt("rental_rate_id");
                int vehicleId = result.getInt("vehicle_id");
                int promotionId = result.getInt("promotion_id");
                int employeeId = result.getInt("employee_id");
                Rental rental = new Rental(rentalDate, returnDate, customerId, insuranceId, rentalRateId, vehicleId, promotionId, employeeId);
                rental.setId(rentalId);
                return rental;
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
    public ArrayList<Rental> get() {
        logger.info("Displaying all the rows from rental table");
        ArrayList<Rental> rentals = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int rentalId = result.getInt("id");
                Date rentalDate = result.getDate("rental_date");
                Date returnDate = result.getDate("return_date");
                int customerId = result.getInt("customer_id");
                int insuranceId = result.getInt("insurance_id");
                int rentalRateId = result.getInt("rental_rate_id");
                int vehicleId = result.getInt("vehicle_id");
                int promotionId = result.getInt("promotion_id");
                int employeeId = result.getInt("employee_id");
                Rental rental = new Rental(rentalDate, returnDate, customerId, insuranceId, rentalRateId, vehicleId, promotionId, employeeId);
                rental.setId(rentalId);
                rentals.add(rental);
            }
            return rentals;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Rental rental) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(rental.getReturnDate().getTime()));
            preparedStatement.setInt(3, rental.getCustomerId());
            preparedStatement.setInt(4, rental.getInsuranceId());
            preparedStatement.setInt(5, rental.getRentalRateId());
            preparedStatement.setInt(6, rental.getVehicleId());
            preparedStatement.setInt(7, rental.getPromotionId());
            preparedStatement.setInt(8, rental.getEmployeeId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rental rental, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(rental.getReturnDate().getTime()));
            preparedStatement.setInt(3, rental.getCustomerId());
            preparedStatement.setInt(4, rental.getInsuranceId());
            preparedStatement.setInt(5, rental.getRentalRateId());
            preparedStatement.setInt(6, rental.getVehicleId());
            preparedStatement.setInt(7, rental.getPromotionId());
            preparedStatement.setInt(8, rental.getEmployeeId());
            preparedStatement.setInt(9, id);
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
