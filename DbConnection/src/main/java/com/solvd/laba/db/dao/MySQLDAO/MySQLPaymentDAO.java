package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Payment;
import java.sql.*;
import java.util.ArrayList;

public class MySQLPaymentDAO extends AbstractDAO<Payment> {
   public MySQLPaymentDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payment WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int paymentId = result.getInt("id");
                Date paymentDate = result.getDate("payment_date");
                double totalAmount = result.getDouble("total_amount");
                int customerId = result.getInt("customer_id");
                int paymentMethodId = result.getInt("payment_method_id");
                Payment payment = new Payment(paymentId, paymentDate, totalAmount, customerId, paymentMethodId);
                System.out.println(payment);
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
    public ArrayList<Payment> selectAll() {
        System.out.println("Displaying all the rows from payment table");
        ArrayList<Payment> payments = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payment");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int paymentId = result.getInt("id");
                Date paymentDate = result.getDate("payment_date");
                double totalAmount = result.getDouble("total_amount");
                int customerId = result.getInt("customer_id");
                int paymentMethodId = result.getInt("payment_method_id");
                Payment payment = new Payment(paymentId, paymentDate, totalAmount, customerId, paymentMethodId);
                payments.add(payment);
            }
            System.out.println(payments);
            return payments;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
        }
        return null;
    }

    @Override
    public void addNewRow(Payment payment) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO payment (payment_date, total_amount, customer_id, payment_method_id) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setDate(1, new java.sql.Date(payment.getPaymentDate().getTime()));
            preparedStatement.setDouble(2, payment.getTotalAmount());
            preparedStatement.setInt(3, payment.getCustomerId());
            preparedStatement.setInt(4, payment.getPaymentMethodId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment,int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String updateStatement = "UPDATE payment SET payment_date=?, total_amount=?, customer_id=?, payment_method_id=? WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
                preparedStatement.setDate(1, new java.sql.Date(payment.getPaymentDate().getTime()));
                preparedStatement.setDouble(2, payment.getTotalAmount());
                preparedStatement.setInt(3, payment.getCustomerId());
                preparedStatement.setInt(4, payment.getPaymentMethodId());
                preparedStatement.setInt(5, id);
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
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String deleteStatement = "DELETE FROM payment WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(deleteStatement);
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
