package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Rental;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class MySQLRentalDAO extends AbstractDAO<Rental> {
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM rental WHERE id=?")) {
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
                System.out.println(rental);
                System.out.println();
                return true;
            } else {
                System.out.println("No record found. Invalid ID");
                System.out.println();
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ArrayList<Rental> selectAll() {
        System.out.println("Displaying all the rows from rental table");
        ArrayList<Rental> rentals = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM rental")) {
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
            System.out.println(rentals);
            return rentals;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Rental rental) {
        String insertStatement = "INSERT INTO rental (rental_date, return_date, customer_id, insurance_id, rental_rate_id, vehicle_id, promotion_id, employee_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertStatement)) {
            preparedStatement.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
            preparedStatement.setDate(2, new java.sql.Date(rental.getReturnDate().getTime()));
            preparedStatement.setInt(3, rental.getCustomerId());
            preparedStatement.setInt(4, rental.getInsuranceId());
            preparedStatement.setInt(5, rental.getRentalRateId());
            preparedStatement.setInt(6, rental.getVehicleId());
            preparedStatement.setInt(7, rental.getPromotionId());
            preparedStatement.setInt(8, rental.getEmployeeId());
            preparedStatement.executeUpdate();
            System.out.println("New row added");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Rental rental,int id) {
        String updateStatement = "UPDATE rental SET rental_date=?, return_date=?, customer_id=?, insurance_id=?, rental_rate_id=?, vehicle_id=?, promotion_id=?, employee_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            if (findById(rental.getId())) {
                preparedStatement.setDate(1, new java.sql.Date(rental.getRentalDate().getTime()));
                preparedStatement.setDate(2, new java.sql.Date(rental.getReturnDate().getTime()));
                preparedStatement.setInt(3, rental.getCustomerId());
                preparedStatement.setInt(4, rental.getInsuranceId());
                preparedStatement.setInt(5, rental.getRentalRateId());
                preparedStatement.setInt(6, rental.getVehicleId());
                preparedStatement.setInt(7, rental.getPromotionId());
                preparedStatement.setInt(8, rental.getEmployeeId());
                preparedStatement.setInt(9, rental.getId());
                preparedStatement.executeUpdate();
                System.out.println("Update complete");
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM rental WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(deleteStatement)) {
            if (findById(id)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
            }
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

}
