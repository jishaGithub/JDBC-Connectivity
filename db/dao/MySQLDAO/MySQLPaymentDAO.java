package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Payment;
import java.sql.*;
import java.util.ArrayList;

public class MySQLPaymentDAO extends AbstractDAO<Payment> {
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM payment WHERE id=?")) {
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
        try (PreparedStatement preparedStatement = getConnection().prepareStatement("SELECT * FROM payment")) {
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
        String insertValueStatement = "INSERT INTO payment (payment_date, total_amount, customer_id, payment_method_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(insertValueStatement)) {
            preparedStatement.setDate(1, new Date(payment.getPaymentDate().getTime()));
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
        String updateStatement = "UPDATE payment SET payment_date=?, total_amount=?, customer_id=?, payment_method_id=? WHERE id=?";
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(updateStatement)) {
            if (findById(id)) {
                preparedStatement.setDate(1, new Date(payment.getPaymentDate().getTime()));
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
        String deleteStatement = "DELETE FROM payment WHERE id=?";
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
