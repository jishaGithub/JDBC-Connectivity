package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.PaymentMethod;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PaymentMethodDAO extends AbstractDAO implements GenericDAO<PaymentMethod> {
    private static final Logger logger = LogManager.getLogger(PaymentMethodDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM payment_method WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM payment_method";
    private final static String INSERT_VALUE = "INSERT INTO payment_method (payment_type) VALUES (?)";
    private final static String UPDATE_VALUE = "UPDATE payment_method SET payment_type=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM payment_method WHERE id=?";

    @Override
    public PaymentMethod get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int paymentMethodId = result.getInt("id");
                String paymentType = result.getString("payment_type");
                return new PaymentMethod(paymentMethodId, paymentType);
            }
            logger.info("No record found. Invalid ID");
        } catch (SQLException e) {
            logger.error("Error executing SQL query:"+e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<PaymentMethod> get() {
        logger.info("Displaying all the rows from payment_method table");
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int paymentMethodId = result.getInt("id");
                String paymentType = result.getString("payment_type");
                PaymentMethod paymentMethod = new PaymentMethod(paymentMethodId, paymentType);
                paymentMethods.add(paymentMethod);
            }
            logger.info(paymentMethods);
            return paymentMethods;
        } catch (SQLException e) {
            logger.error("Error executing SQL query");
        }
        return null;
    }

    @Override
    public void add(PaymentMethod paymentMethod) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUE)) {
            preparedStatement.setString(1, paymentMethod.getPaymentType());
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.error("Error executing SQL query");
        }
    }

    @Override
    public void update(PaymentMethod paymentMethod, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, paymentMethod.getPaymentType());
            preparedStatement.setInt(2, id);
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
            logger.error("Error executing SQL query");
        }
    }
}
