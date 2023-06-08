package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Insurance;
import java.sql.*;
import java.util.ArrayList;

public class MySQLInsuranceDAO extends AbstractDAO<Insurance> {
    public MySQLInsuranceDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }

    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM insurance WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int insuranceId = result.getInt("id");
                String insuranceName = result.getString("insurance_name");
                double coverage = result.getDouble("coverage");
                int customerId = result.getInt("customer_id");
                Insurance insurance = new Insurance(insuranceId, insuranceName, coverage, customerId);
                System.out.println(insurance);
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
    public ArrayList<Insurance> selectAll() {
        System.out.println("Displaying all the rows from insurance table");
        ArrayList<Insurance> insurances = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM insurance");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int insuranceId = result.getInt("id");
                String insuranceName = result.getString("insurance_name");
                double coverage = result.getDouble("coverage");
                int customerId = result.getInt("customer_id");
                Insurance insurance = new Insurance(insuranceId, insuranceName, coverage, customerId);
                insurances.add(insurance);
            }
            System.out.println(insurances);
            return insurances;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Insurance insurance) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO insurance (insurance_name, coverage, customer_id) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, insurance.getInsuranceName());
            preparedStatement.setDouble(2, insurance.getCoverage());
            preparedStatement.setInt(3, insurance.getCustomerId());
            preparedStatement.executeUpdate();
            System.out.println("Insertion complete");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Insurance insurance, int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String updateStatement = "UPDATE insurance SET insurance_name=?, coverage=?, customer_id=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
            preparedStatement.setString(1, insurance.getInsuranceName());
            preparedStatement.setDouble(2, insurance.getCoverage());
            preparedStatement.setInt(3, insurance.getCustomerId());
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
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(id)) {
                String deleteStatement = "DELETE FROM insurance WHERE id=?";
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
