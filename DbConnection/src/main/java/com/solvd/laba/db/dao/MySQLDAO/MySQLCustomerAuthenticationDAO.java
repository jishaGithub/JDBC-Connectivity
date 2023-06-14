package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.CustomerAuthentication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class MySQLCustomerAuthenticationDAO extends AbstractDAO<CustomerAuthentication> {
    private static final Logger logger = LogManager.getLogger(MySQLCustomerAuthenticationDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM customer_authentication WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM customer_authentication";
    private final static String INSERT_VALUES = "INSERT INTO customer_authentication (user_name, password, customer_id) VALUES (?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE customer_authentication SET user_name=?, password=?, customer_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM customer_authentication WHERE id=?";

    @Override
    public CustomerAuthentication get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int authId = result.getInt("id");
                String userName = result.getString("user_name");
                String password = result.getString("password");
                int customerId = result.getInt("customer_id");
                return new CustomerAuthentication(authId, userName, password, customerId);
            } else {
                logger.info("No record found. Invalid ID");
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            logger.error(e.getMessage(), e);
        }
        return null;
    }

    @Override
    public ArrayList<CustomerAuthentication> get() {
        logger.info("Displaying all rows from customer_authentication table...");
        ArrayList<CustomerAuthentication> customerAuthList = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int authId = result.getInt("id");
                String userName = result.getString("user_name");
                String password = result.getString("password");
                int customerId = result.getInt("customer_id");
                CustomerAuthentication customerAuth = new CustomerAuthentication(authId, userName, password, customerId);
                customerAuthList.add(customerAuth);
            }
        } catch (SQLException e) {
            logger.info("Error executing SQL query:");
            logger.error(e.getMessage(), e);
        }
        logger.info(customerAuthList);
        return customerAuthList;
    }

    @Override
    public void add(CustomerAuthentication row) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, row.getUserName());
            preparedStatement.setString(2, row.getPassword());
            preparedStatement.setInt(3, row.getCustomerId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:"+e.getMessage());
        }
    }

    @Override
    public void update(CustomerAuthentication customerAuth, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, customerAuth.getUserName());
            preparedStatement.setString(2, customerAuth.getPassword());
            preparedStatement.setInt(3, customerAuth.getCustomerId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query"+e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(DELETE_VALUE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            logger.info("Deletion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:"+e.getMessage());
        }
    }
}
