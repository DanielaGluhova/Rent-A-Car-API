package com.fmi.Rent_A_Car.entities;

public class RentalDetails {
    private int id;
    private int rentalDays;
    private int weekendDays;

    public RentalDetails(int id, int rentalDays, int weekendDays) {
        this.id = id;
        this.rentalDays = rentalDays;
        this.weekendDays = weekendDays;
    }

    public RentalDetails() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRentalDays() {
        return rentalDays;
    }

    public void setRentalDays(int rentalDays) {
        this.rentalDays = rentalDays;
    }

    public int getWeekendDays() {
        return weekendDays;
    }

    public void setWeekendDays(int weekendDays) {
        this.weekendDays = weekendDays;
    }
}
