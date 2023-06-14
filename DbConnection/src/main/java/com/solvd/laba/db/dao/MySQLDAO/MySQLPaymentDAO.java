package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Payment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.*;
import java.util.ArrayList;

public class MySQLPaymentDAO extends AbstractDAO<Payment> {
    private static final Logger logger = LogManager.getLogger(MySQLPaymentDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM payment WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM payment";
    private final static String INSERT_VALUES = "INSERT INTO payment (payment_date, total_amount, customer_id, payment_method_id) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE payment SET payment_date=?, total_amount=?, customer_id=?, payment_method_id=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM payment WHERE id=?";

    @Override
    public Payment get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int paymentId = result.getInt("id");
                Date paymentDate = result.getDate("payment_date");
                double totalAmount = result.getDouble("total_amount");
                int customerId = result.getInt("customer_id");
                int paymentMethodId = result.getInt("payment_method_id");
                return new Payment(paymentId, paymentDate, totalAmount, customerId, paymentMethodId);
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
    public ArrayList<Payment> get() {
        logger.info("Displaying all the rows from payment table");
        ArrayList<Payment> payments = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
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
            logger.info(payments);
            return payments;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Payment payment) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setDate(1, new Date(payment.getPaymentDate().getTime()));
            preparedStatement.setDouble(2, payment.getTotalAmount());
            preparedStatement.setInt(3, payment.getCustomerId());
            preparedStatement.setInt(4, payment.getPaymentMethodId());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Payment payment, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setDate(1,  new Date(payment.getPaymentDate().getTime()));
            preparedStatement.setDouble(2, payment.getTotalAmount());
            preparedStatement.setInt(3, payment.getCustomerId());
            preparedStatement.setInt(4, payment.getPaymentMethodId());
            preparedStatement.setInt(5, id);
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
