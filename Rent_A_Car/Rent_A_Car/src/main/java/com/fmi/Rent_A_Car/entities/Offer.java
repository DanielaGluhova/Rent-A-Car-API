package com.fmi.Rent_A_Car.entities;

public class Offer {
    private int id;
    private int client_id;
    private int car_id;
    private int rental_details_id;
    private RentalDetails rentalDetails;

    public RentalDetails getRentalDetails() {
        return rentalDetails;
    }

    public void setRentalDetails(RentalDetails rentalDetails) {
        this.rentalDetails = rentalDetails;
    }

    public int getRental_details_id() {
        return rental_details_id;
    }

    public void setRental_details_id(int rental_details_id) {
        this.rental_details_id = rental_details_id;
    }

    private String status;
    private int rental_days;
    private int is_deleted = 0;

    public Offer(int id, int client_id, int car_id, int rental_details_id, String status, int is_deleted) {
        this.id = id;
        this.client_id = client_id;
        this.car_id = car_id;
        this.rental_details_id = rental_details_id;
        this.status = status;
        this.is_deleted = is_deleted;
    }


    public Offer() {

    }

    public int getRental_days() {
        return rental_days;
    }

    public void setRental_days(int rental_days) {
        this.rental_days = rental_days;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClient_id() {
        return client_id;
    }

    public void setClient_id(int client_id) {
        this.client_id = client_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
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

