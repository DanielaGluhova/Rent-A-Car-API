package com.fmi.Rent_A_Car.controllers;

import com.fmi.Rent_A_Car.entities.*;
import com.fmi.Rent_A_Car.http.AppResponse;
import com.fmi.Rent_A_Car.services.CarService;
import com.fmi.Rent_A_Car.services.ClientService;
import com.fmi.Rent_A_Car.services.OfferService;
import com.fmi.Rent_A_Car.services.RentalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/rentaldetails")
public class RentalDetailsController {

    private final RentalDetailsService rentalDetailsService;
    private final CarService carService;
    private final OfferService offerService;
    private final ClientService clientService;

    @Autowired
    public RentalDetailsController(RentalDetailsService rentalDetailsService, CarService carService, ClientService clientService, OfferService offerService) {
        this.rentalDetailsService = rentalDetailsService;
        this.carService = carService;
        this.offerService = offerService;
        this.clientService = clientService;
    }

    @GetMapping("/calculatePrice")
    public ResponseEntity<?> calculateRentalPrice(@RequestParam("offer_id") int offer_id) {
        // Вземаме свързаните клиент и кола от базата данни
        Offer offer = offerService.getOffer(offer_id);
        Car car = carService.getCarById(offer.getCar_id());
        Client client = clientService.getClient(offer.getClient_id());
        RentalDetails rentalDetails = rentalDetailsService.getRentalDetailsById(offer.getRental_details_id());

        // Изчисляваме детайлите за наемане
        double finalPrice = calculateRentalDetails(rentalDetails, car, client);

        // Връщаме крайната цена в отговора
        return AppResponse.success()
                .withMessage("Total rental price calculated successfully")
                .withData(finalPrice)
                .build();
    }

    // Пресмятане на наемната цена на базата на офертата
    public double calculateRentalDetails(RentalDetails rentalDetails, Car car, Client client) {
        double dailyRate = car.getDaily_rate();

        // Базова цена
        double basePrice = rentalDetails.getRentalDays() * dailyRate;

        // Допълнителна такса за инциденти
        double additionalFee = client.getHas_incidents() == 1 ? 200 : 0;

        // Такса за уикенд дни
        double weekendSurcharge = rentalDetails.getWeekendDays() * dailyRate * 0.10;

        // Крайна цена
        return basePrice + additionalFee + weekendSurcharge;
    }


    // Създаване на нови детайли за наем
    @PostMapping
    public ResponseEntity<?> createRentalDetails(@RequestBody RentalDetails rentalDetails) {
        return ResponseEntity.ok().body("Rental details created successfully!");
    }

    // Листване на всички наемни детайли
    @GetMapping
    public ResponseEntity<?> listAllRentalDetails() {
        ArrayList<RentalDetails> rentalDetails = (ArrayList<RentalDetails>) this.rentalDetailsService.getAllRentalDetails();
        return AppResponse.success().withData(rentalDetails).build();
    }

    // Листване на конкретни наемни детайли по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getRentalDetails(@PathVariable Long id) {
        return ResponseEntity.ok().body("Rental details for ID " + id);
    }
}

