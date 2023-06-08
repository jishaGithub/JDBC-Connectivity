package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Customer;
import java.sql.*;
import java.util.ArrayList;

public class MySQLCustomerDAO extends AbstractDAO<Customer> {
    public MySQLCustomerDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id1) {
        System.out.println("Finding record of ID:" + id1 + ".....");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer where id=?");
            preparedStatement.setInt(1, id1);
            ResultSet result = preparedStatement.executeQuery();
            result.next();
            int id = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String phoneNumber = result.getString("phone_number");
            String email = result.getString("email");
            int age = result.getInt("age");
            Customer customer1 = new Customer(id, firstName, lastName, email, phoneNumber, age);
            System.out.println(customer1);
            System.out.println(" ");
            return true;
        } catch (SQLException se) {
            System.out.println("No record found. Invalid ID");
            System.out.println(" ");
            return false;
        }
    }

    @Override
    public ArrayList<Customer> selectAll() {
        System.out.println("Displaying all the rows from customer table");
        ArrayList<Customer> customers = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM customer");
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
            System.out.println(customers);
            return customers;
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Customer row) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO customer(first_name,last_name,phone_number,email,age) VALUES (?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, row.getFirstName());
            preparedStatement.setString(2, row.getLastName());
            preparedStatement.setString(3, row.getPhoneNumber());
            preparedStatement.setString(4, row.getEmail());
            preparedStatement.setInt(5, row.getAge());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
            System.out.println(selectAll());
        } catch (SQLException e) {
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE customer SET first_name=?,last_name=?,phone_number=?,email=?,age=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getPhoneNumber());
            preparedStatement.setString(4, customer.getEmail());
            preparedStatement.setInt(5, customer.getAge());
            preparedStatement.setInt(6, id);
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
                String deleteStatement = "DELETE from customer WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
                System.out.println("Deletion complete");
                System.out.println(selectAll());
            }
        } catch (SQLException e) {
            System.out.println("Deletion unsuccessful!");
            e.printStackTrace();
        }
    }

}
