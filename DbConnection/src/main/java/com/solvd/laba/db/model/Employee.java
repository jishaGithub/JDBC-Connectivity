package com.solvd.laba.db.model;

import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "employee")
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    @XmlAttribute
    private int id;
    @XmlElement
    private String name;
    @XmlElement(name = "employeeEmail")
    private String email;
    @XmlElement(name = "branchId")
    private int branchId;

    public Employee() {

    }

    public Employee (int id, String name, String email, int branchId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.branchId = branchId;
    }

    public Employee (String name, String email, int branchId) {
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
