package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.CustomerAuthentication;
import java.sql.*;
import java.util.ArrayList;

public class MySQLCustomerAuthenticationDAO extends AbstractDAO<CustomerAuthentication> {
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM customer_authentication WHERE id=?")) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int authId = result.getInt("id");
                String userName = result.getString("user_name");
                String password = result.getString("password");
                int customerId = result.getInt("customer_id");
                CustomerAuthentication customerAuth = new CustomerAuthentication(authId, userName, password, customerId);
                System.out.println(customerAuth);
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
    public ArrayList<CustomerAuthentication> selectAll() {
        System.out.println("Displaying all rows from customer_authentication table...");
        ArrayList<CustomerAuthentication> customerAuthList = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM customer_authentication")) {
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
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        System.out.println(customerAuthList);
        return customerAuthList;
    }

    @Override
    public void addNewRow (CustomerAuthentication row) {
        String insertValueStatement = "INSERT INTO customer_authentication (user_name, password, customer_id) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setString(1, row.getUserName());
            preparedStatement.setString(2, row.getPassword());
            preparedStatement.setInt(3, row.getCustomerId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(CustomerAuthentication customerAuth, int id) {
        String updateStatement = "UPDATE customer_authentication SET user_name=?, password=?, customer_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            preparedStatement.setString(1, customerAuth.getUserName());
            preparedStatement.setString(2, customerAuth.getPassword());
            preparedStatement.setInt(3, customerAuth.getCustomerId());
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
            System.out.println("Update complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String deleteStatement = "DELETE FROM customer_authentication WHERE id=?";
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
