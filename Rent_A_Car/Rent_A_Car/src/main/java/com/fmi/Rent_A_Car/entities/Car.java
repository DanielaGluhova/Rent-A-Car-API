package com.fmi.Rent_A_Car.entities;

import java.math.BigDecimal;

public class Car {

    private int id;
    private String model;
    private int car_year;
    private double daily_rate;
    private String location;
    private String status;
    private int is_deleted = 0;

    public Car(int id, String model, int car_year, double daily_rate, String location, String status, int is_deleted) {
        this.id = id;
        this.model = model;
        this.car_year = car_year;
        this.daily_rate = daily_rate;
        this.location = location;
        this.status = status;
        this.is_deleted = is_deleted;
    }

    public Car() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getCar_year() {
        return car_year;
    }

    public void setCar_year(int car_year) {
        this.car_year = car_year;
    }

    public double getDaily_rate() {
        return daily_rate;
    }

    public void setDaily_rate(double daily_rate) {
        this.daily_rate = daily_rate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }
}
