package com.solvd.laba.db.model;

public class Branch {
    private int id;
    private String branchName;
    private int branchLocationId;

    public Branch(int id, String branchName, int branchLocationId) {
        this.id = id;
        this.branchName = branchName;
        this.branchLocationId = branchLocationId;
    }    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public int getBranchLocationId() {
        return branchLocationId;
    }

    public void setBranchLocationId(int branchLocationId) {
        this.branchLocationId = branchLocationId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", branchName='" + branchName + '\'' +
                ", branchLocationId=" + branchLocationId +
                '}'+"\n";
    }
}
