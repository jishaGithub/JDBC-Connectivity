package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.PaymentMethod;
import java.sql.*;
import java.util.ArrayList;

public class MySQLPaymentMethodDAO extends AbstractDAO<PaymentMethod> {
    public MySQLPaymentMethodDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payment_method WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int paymentMethodId = result.getInt("id");
                String paymentType = result.getString("payment_type");
                PaymentMethod paymentMethod = new PaymentMethod(paymentMethodId, paymentType);
                System.out.println(paymentMethod);
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
    public ArrayList<PaymentMethod> selectAll() {
        System.out.println("Displaying all the rows from payment_method table");
        ArrayList<PaymentMethod> paymentMethods = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM payment_method");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int paymentMethodId = result.getInt("id");
                String paymentType = result.getString("payment_type");
                PaymentMethod paymentMethod = new PaymentMethod(paymentMethodId, paymentType);
                paymentMethods.add(paymentMethod);
            }
            System.out.println(paymentMethods);
            return paymentMethods;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(PaymentMethod paymentMethod) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO payment_method (payment_type) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, paymentMethod.getPaymentType());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(PaymentMethod paymentMethod, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String updateStatement = "UPDATE payment_method SET payment_type=? WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
                preparedStatement.setString(1, paymentMethod.getPaymentType());
                preparedStatement.setInt(2, id);
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
                String deleteStatement = "DELETE FROM payment_method WHERE id=?";
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
