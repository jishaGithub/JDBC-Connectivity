package com.solvd.laba.db.service;

import com.solvd.laba.db.dao.PromotionDAO;
import com.solvd.laba.db.model.Promotion;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.laba.db.util.GenericDAO;
import java.util.ArrayList;

public class PromotionService implements GenericDAO<Promotion> {
    private static final Logger logger = LogManager.getLogger(PromotionService.class);
    private PromotionDAO promotionDAO;

    public PromotionService(PromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    @Override
    public Promotion get(int id) {
        return promotionDAO.get(id);
    }

    @Override
    public ArrayList<Promotion> get() {
        return promotionDAO.get();
    }

    @Override
    public void add(Promotion promotion) {
        promotionDAO.add(promotion);
    }

    @Override
    public void update(Promotion promotion, int id) {
        promotionDAO.update(promotion, id);
    }

    @Override
    public void delete(int id) {
        promotionDAO.delete(id);
    }
}
