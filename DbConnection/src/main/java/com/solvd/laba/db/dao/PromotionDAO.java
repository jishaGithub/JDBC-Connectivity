package com.solvd.laba.db.dao;

import com.solvd.laba.db.model.Promotion;
import com.solvd.laba.db.util.GenericDAO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class PromotionDAO extends AbstractDAO implements GenericDAO<Promotion> {
    private static final Logger logger = LogManager.getLogger(PromotionDAO.class);
    private final static String GET_BY_ID = "SELECT * FROM promotion WHERE id=?";
    private final static String GET_ALL = "SELECT * FROM promotion";
    private final static String INSERT_VALUES = "INSERT INTO promotion (promotion_name, discount, start_date, end_date) VALUES (?, ?, ?, ?)";
    private final static String UPDATE_VALUE = "UPDATE promotion SET promotion_name=?, discount=?, start_date=?, end_date=? WHERE id=?";
    private final static String DELETE_VALUE = "DELETE FROM promotion WHERE id=?";

    @Override
    public Promotion get(int id) {
        logger.info("Finding record of ID: " + id + "...");
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_BY_ID)) {
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
                return promotion;
            }
            logger.info("No record found. Invalid ID");
        } catch (SQLException e) {
            logger.info("Error executing SQL query:" + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Promotion> get() {
        logger.info("Displaying all the rows from promotion table");
        ArrayList<Promotion> promotions = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(GET_ALL)) {
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
            logger.info(promotions);
            return promotions;
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void add(Promotion promotion) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(INSERT_VALUES)) {
            preparedStatement.setString(1, promotion.getPromotionName());
            preparedStatement.setBigDecimal(2, promotion.getDiscount());
            preparedStatement.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
            preparedStatement.executeUpdate();
            logger.info("Insertion complete");
        } catch (SQLException e) {
            logger.info("Error executing SQL query");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Promotion promotion, int id) {
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(UPDATE_VALUE)) {
            preparedStatement.setString(1, promotion.getPromotionName());
            preparedStatement.setBigDecimal(2, promotion.getDiscount());
            preparedStatement.setDate(3, new java.sql.Date(promotion.getStartDate().getTime()));
            preparedStatement.setDate(4, new java.sql.Date(promotion.getEndDate().getTime()));
            preparedStatement.setInt(5, id);
            preparedStatement.executeUpdate();
            logger.info("Update complete");
        } catch (SQLException e) {
            logger.info("Update error:"+e.getMessage());
            logger.info("Error! id is null. Use another non-null id");
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
