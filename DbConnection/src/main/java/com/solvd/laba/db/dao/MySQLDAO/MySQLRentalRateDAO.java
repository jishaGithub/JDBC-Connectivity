package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.RentalRate;
import java.sql.*;
import java.util.ArrayList;

public class MySQLRentalRateDAO extends AbstractDAO<RentalRate> {
    public MySQLRentalRateDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rental_rate WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int rentalId = result.getInt("id");
                String rentalType = result.getString("rental_type");
                double rentalAmount = result.getDouble("rental_amount");
                Integer minDuration = result.getInt("min_duration");
                Integer maxDuration = result.getInt("max_duration");
                RentalRate rentalRate = new RentalRate(rentalId, rentalType, rentalAmount, minDuration, maxDuration);
                System.out.println(rentalRate);
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
    public ArrayList<RentalRate> selectAll() {
        System.out.println("Displaying all the rows from rental_rate table");
        ArrayList<RentalRate> rentalRates = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM rental_rate");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int rentalId = result.getInt("id");
                String rentalType = result.getString("rental_type");
                double rentalAmount = result.getDouble("rental_amount");
                Integer minDuration = result.getInt("min_duration");
                Integer maxDuration = result.getInt("max_duration");
                RentalRate rentalRate = new RentalRate(rentalId, rentalType, rentalAmount, minDuration, maxDuration);
                rentalRates.add(rentalRate);
            }
            System.out.println(rentalRates);
            return rentalRates;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(RentalRate rentalRate) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO rental_rate(rental_type, rental_amount, min_duration, max_duration) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, rentalRate.getRentalType());
            preparedStatement.setDouble(2, rentalRate.getRentalAmount());
            preparedStatement.setInt(3, rentalRate.getMinDuration());
            preparedStatement.setInt(4, rentalRate.getMaxDuration());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void update(RentalRate rentalRate, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE rental_rate SET rental_type=?, rental_amount=?, min_duration=?, max_duration=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, rentalRate.getRentalType());
            preparedStatement.setDouble(2, rentalRate.getRentalAmount());
            preparedStatement.setInt(3, rentalRate.getMinDuration());
            preparedStatement.setInt(4, rentalRate.getMaxDuration());
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL statement");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String deleteStatement = "DELETE FROM rental_rate WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
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
