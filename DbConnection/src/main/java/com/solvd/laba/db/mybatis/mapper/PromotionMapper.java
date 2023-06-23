package com.solvd.laba.db.mybatis.mapper;

import com.solvd.laba.db.model.Promotion;

import java.util.List;

public interface PromotionMapper {
    void insertPromotion(Promotion promotion);

    void updatePromotion(Promotion promotion);

    void deletePromotion(int promotionId);

    Promotion getPromotionById(int promotionId);

    List<Promotion> getAllPromotions();
}
