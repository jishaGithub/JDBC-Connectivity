package com.solvd.laba.db.model;

public class VehicleCategory {
    private int id;
    private String categoryName;

    public VehicleCategory(int id, String categoryName) {
        this.id = id;
        this.categoryName = categoryName;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                '}'+"\n";
    }
}
