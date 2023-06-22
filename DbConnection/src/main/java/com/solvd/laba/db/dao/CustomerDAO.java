package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.Customer;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO extends AbstractDAO implements GenericDAO<Customer> {
    private static final Logger logger = LogManager.getLogger(CustomerDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM customer WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM customer";
    private final static String INSERT_VALUES = "INSERT INTO customer(first_name, last_name, phone_number, email, age) VALUES (?, ?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE customer SET first_name=?, last_name=?, phone_number=?, email=?, age=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM customer WHERE id=?";

    @Override
    public Customer get(int id) {
        logger.info("Finding record of ID: " + id + ".....");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int customerId = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String phoneNumber = result.getString("phone_number");
                String email = result.getString("email");
                int age = result.getInt("age");
                Customer customer = new Customer(customerId, firstName, lastName, email, phoneNumber, age);
                logger.info(customer);
                return customer;
            }
            logger.info("No record found. Invalid ID");
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Customer> get() {
        logger.info("Displaying all the rows from customer table");
        ArrayList<Customer> customers = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int id = result.getInt("id");
                String firstName = result.getString("first_name");
                String lastName = result.getString("last_name");
                String phoneNumber = result.getString("phone_number");
                String email = result.getString("email");
                int age = result.getInt("age");
                Customer customer = new Customer(id, firstName, lastName, email, phoneNumber, age);
                customers.add(customer);
            }
            logger.info(customers);
            return customers;
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
        return null;
    }

    @Override
    public void add(Customer row) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, row.getFirstName());
            preparedStatement.setString(2, row.getLastName());
            preparedStatement.setString(3, row.getPhoneNumber());
            preparedStatement.setString(4, row.getEmail());
            preparedStatement.setInt(5, row.getAge());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.error("SQL query error:"+e.getMessage());
        }
    }

    @Override
    public void update(Customer customer, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getAge());
            preparedStatement.setInt(6, id);
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
            logger.info("Deletion unsuccessful!"+e.getMessage());
        }
    }
}
