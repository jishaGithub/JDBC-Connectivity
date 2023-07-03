package com.solvd.laba.db.service.mybatis;

import com.solvd.laba.db.model.Promotion;
import com.solvd.laba.db.mybatis.exceptions.RecordNotFoundException;
import com.solvd.laba.db.mybatis.mapper.PromotionMapper;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class MyBatisPromotionService implements PromotionMapper {
    private static final Logger logger = LogManager.getLogger(MyBatisPromotionService.class);
    private final PromotionMapper promotionMapper;
    private SqlSession sqlSession;

    public MyBatisPromotionService(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
        this.promotionMapper = sqlSession.getMapper(PromotionMapper.class);
    }

    public void insertPromotion(Promotion promotion) {
        try {
            if (promotionMapper.getPromotionById(promotion.getId()) != null) {
                throw new Exception("Duplicate primary key. Insertion not allowed.");
            }
            promotionMapper.insertPromotion(promotion);
            logger.info("Insertion complete");
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void updatePromotion(Promotion promotion) {
        try {
            if (promotionMapper.getPromotionById(promotion.getId()) == null) {
                logger.error("Updation Unsuccessful! for promotion Id:" + promotion.getId());
                throw new RecordNotFoundException(promotion.getId(), "Promotion");
            }
            promotionMapper.updatePromotion(promotion);
            logger.info("Updation complete for promotion Id:" + promotion.getId());
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void deletePromotion(int promotionId) {
        try {
            if (promotionMapper.getPromotionById(promotionId) == null) {
                logger.error("Deletion Unsuccessful for promotionId:" + promotionId);
                throw new RecordNotFoundException(promotionId, "Promotion");
            }
            logger.info("Deletion complete for " + promotionId);
            promotionMapper.deletePromotion(promotionId);
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
    }

    public Promotion getPromotionById(int promotionId) {
        try {
            if (promotionMapper.getPromotionById(promotionId) == null) {
                throw new RecordNotFoundException(promotionId, "Promotion");
            }
            Promotion promotion = promotionMapper.getPromotionById(promotionId);
            logger.info(promotion);
            return promotion;
        } catch (RecordNotFoundException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    public List<Promotion> getAllPromotions() {
        logger.info(promotionMapper.getAllPromotions());
        return promotionMapper.getAllPromotions();
    }
}
