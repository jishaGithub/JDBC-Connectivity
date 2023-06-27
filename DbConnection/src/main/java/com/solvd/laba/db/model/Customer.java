package com.solvd.laba.db.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name= "customer")
public class Customer {
    @XmlAttribute
    @JsonProperty("id")
    private int id;
    @XmlElement
    @JsonProperty("firstName")
    private String firstName;
    @XmlElement
    @JsonProperty("lastName")
    private String lastName;
    @XmlElement(name = "customerEmail")
    @JsonProperty("email")
    private String email;
    @XmlElement
    @JsonProperty("phoneNumber")
    private String phoneNumber;
    @XmlElement
    @JsonProperty("age")
    private int age;
    public Customer(){
    }

    public Customer(int id, String firstName, String lastName, String email, String phoneNumber,int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "{ " +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                " }"+"\n";
    }
}
