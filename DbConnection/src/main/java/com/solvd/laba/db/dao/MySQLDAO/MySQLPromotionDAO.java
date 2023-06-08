package com.solvd.laba.db.dao.MySQLDAO;

import com.solvd.laba.db.dao.AbstractDAO;
import com.solvd.laba.db.model.Promotion;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class MySQLPromotionDAO extends AbstractDAO<Promotion> {
    public MySQLPromotionDAO() {
        ConfigFileDAO.loadPropertyConfigFile();
    }
    @Override
    public Boolean findById(int id) {
        System.out.println("Finding record of ID: " + id + "...");
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM promotion WHERE id=?");
            preparedStatement.setInt(1, id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                int promotionId = result.getInt("id");
                String promotionName = result.getString("promotion_name");
                BigDecimal discount = result.getBigDecimal("discount");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");
                Promotion promotion = new Promotion(promotionName, discount, startDate, endDate);
                promotion.setId(promotionId);
                System.out.println(promotion);
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
    public ArrayList<Promotion> selectAll() {
        System.out.println("Displaying all the rows from promotion table");
        ArrayList<Promotion> promotions = new ArrayList<>();
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM promotion");
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()) {
                int promotionId = result.getInt("id");
                String promotionName = result.getString("promotion_name");
                BigDecimal discount = result.getBigDecimal("discount");
                Date startDate = result.getDate("start_date");
                Date endDate = result.getDate("end_date");
                Promotion promotion = new Promotion(promotionName, discount, startDate, endDate);
                promotion.setId(promotionId);
                promotions.add(promotion);
            }
            System.out.println(promotions);
            return promotions;
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addNewRow(Promotion promotion) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            String insertValueStatement = "INSERT INTO promotion (promotion_name, discount, start_date, end_date) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertValueStatement);
            preparedStatement.setString(1, promotion.getPromotionName());
            preparedStatement.setBigDecimal(2, promotion.getDiscount());
            preparedStatement.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
            preparedStatement.executeUpdate();
            System.out.println("New row added");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Promotion promotion,int id) {
        try (Connection connection = ConfigFileDAO.getDataSource().getConnection()) {
            if (findById(promotion.getId())) {
                String updateStatement = "UPDATE promotion SET promotion_name=?, discount=?, start_date=?, end_date=? WHERE id=?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateStatement);
                preparedStatement.setString(1, promotion.getPromotionName());
                preparedStatement.setBigDecimal(2, promotion.getDiscount());
                preparedStatement.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
                preparedStatement.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
                preparedStatement.setInt(5, promotion.getId());
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
                String deleteStatement = "DELETE FROM promotion WHERE id=?";
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
