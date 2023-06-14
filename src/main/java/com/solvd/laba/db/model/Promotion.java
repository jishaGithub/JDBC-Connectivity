package com.solvd.laba.db.model;

import java.math.BigDecimal;
import java.util.Date;

public class Promotion {
    private int id;
    private String promotionName;
    private BigDecimal discount;
    private Date startDate;
    private Date endDate;

    public Promotion(String promotionName, BigDecimal discount, Date startDate, Date endDate) {
        this.promotionName = promotionName;
        this.discount = discount;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionName() {
        return promotionName;
    }

    public void setPromotionName(String promotionName) {
        this.promotionName = promotionName;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", promotionName='" + promotionName + '\'' +
                ", discount=" + discount +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}'+"\n";
    }
}

