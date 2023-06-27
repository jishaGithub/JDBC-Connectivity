package com.solvd.laba.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;

@XmlRootElement(name = "authentication")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerAuthentication {
    @XmlAttribute
    @JsonProperty("id")
    private int id;
    @XmlElement(name = "userName", required = true)
    @JsonProperty("userName")
    private String userName;
    @XmlElement(name = "password", required = true)
    @JsonProperty("password")
    private String password;
    @XmlElement(name = "customerId")

    @JsonProperty("customerId") 
    private int customerId;

    public CustomerAuthentication() {}
    public CustomerAuthentication(int id, String userName, String password, Integer customerId) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", customerId=" + customerId +
                '}'+"\n";
    }
}
