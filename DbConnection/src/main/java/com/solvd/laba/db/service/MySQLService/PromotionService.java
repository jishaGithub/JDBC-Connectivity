package com.solvd.laba.db.service.MySQLService;

import com.solvd.laba.db.dao.MySQLDAO.MySQLPromotionDAO;
import com.solvd.laba.db.model.Promotion;
import com.solvd.laba.db.service.DAOService;

public class PromotionService implements DAOService<Promotion> {
    private MySQLPromotionDAO promotionDAO;
    public PromotionService(MySQLPromotionDAO promotionDAO) {
        this.promotionDAO = promotionDAO;
    }

    @Override
    public void findById(int id) {
        promotionDAO.findById(id);
    }

    @Override
    public void selectAll() {
        promotionDAO.selectAll();
    }

    @Override
    public void newRow(Promotion promotion) {
        promotionDAO.addNewRow(promotion);
    }

    @Override
    public void updateRow(Promotion promotion, int id) {
        promotionDAO.update(promotion, id);
    }

    @Override
    public void deleteRow(int id) {
        promotionDAO.delete(id);
    }
}
