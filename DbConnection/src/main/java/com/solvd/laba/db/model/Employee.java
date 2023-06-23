package com.solvd.laba.db.model;

public class Employee {
    private int id;
    private String name;
    private String email;
    private int branchId;

    public Employee (int id, String name, String email, int branchId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branchId = branchId;
    }   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", branchId=" + branchId +
                '}'+"\n";
    }
}
