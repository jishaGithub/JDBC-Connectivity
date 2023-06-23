package com.solvd.laba.db.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.solvd.laba.db.xml.jaxb.CustomDate;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.util.Date;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name= "rental")
public class Rental {

    @XmlAttribute
    @JsonProperty("id")
    private int id;
    @XmlElement(name = "rentalDate")
    @XmlJavaTypeAdapter(CustomDate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("rentalDate")
    private Date rentalDate;
    @XmlElement(name = "returnDate")
    @XmlJavaTypeAdapter(CustomDate.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonProperty("returnDate")
    private Date returnDate;
    @XmlElement(name ="customerId")
    @JsonProperty("customerId")
    private int customerId;
    @XmlTransient
    @JsonIgnore
    private int insuranceId;
    @XmlTransient
    @JsonIgnore
    private int rentalRateId;
    @XmlElement(name="vehicleId")
    @JsonProperty("vehicleId")
    private int vehicleId;
    @XmlTransient
    @JsonIgnore
    private int promotionId;
    @XmlTransient
    @JsonIgnore
    private int employeeId;

    public Rental() {
    }

    public Rental(int id, Date rentalDate, Date returnDate, int customerId, int insuranceId,
                  int rentalRateId, int vehicleId, int promotionId, int employeeId) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.customerId = customerId;
        this.insuranceId = insuranceId;
        this.rentalRateId = rentalRateId;
        this.vehicleId = vehicleId;
        this.promotionId = promotionId;
        this.employeeId = employeeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(int insuranceId) {
        this.insuranceId = insuranceId;
    }

    public int getRentalRateId() {
        return rentalRateId;
    }

    public void setRentalRateId(int rentalRateId) {
        this.rentalRateId = rentalRateId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getPromotionId() {
        return promotionId;
    }

    public void setPromotionId(int promotionId) {
        this.promotionId = promotionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", customerId=" + customerId +
                ", insuranceId=" + insuranceId +
                ", rentalRateId=" + rentalRateId +
                ", vehicleId=" + vehicleId +
                ", promotionId=" + promotionId +
                ", employeeId=" + employeeId +
                '}'+"\n";
    }
}