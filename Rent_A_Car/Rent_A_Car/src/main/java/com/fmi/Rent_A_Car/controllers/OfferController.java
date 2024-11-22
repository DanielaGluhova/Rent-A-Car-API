package com.fmi.Rent_A_Car.controllers;


import com.fmi.Rent_A_Car.entities.Offer;
import com.fmi.Rent_A_Car.entities.RentalDetails;
import com.fmi.Rent_A_Car.http.AppResponse;
import com.fmi.Rent_A_Car.services.OfferService;
import com.fmi.Rent_A_Car.services.RentalDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final RentalDetailsService rentalDetailsService;

    public OfferController(OfferService offerService, RentalDetailsService rentalDetailsService) {
        this.offerService = offerService;
        this.rentalDetailsService = rentalDetailsService;
    }

    // Създаване на нова оферта
    @PostMapping
    public ResponseEntity<?> createOffer(@RequestBody Offer offer) {
       int  rentalDetailsID = rentalDetailsService.addRentalDetails(offer.getRentalDetails());
        offer.setRental_details_id(rentalDetailsID);
            if (offerService.createOffer(offer) ) {
            return AppResponse.success()
                    .withMessage("Offer created successfully")
                    .build();
        }
        return AppResponse.error()
                .withMessage("Offer could not be created")
                .build();
    }

    // Листване на всички оферти за конкретен клиент
    @GetMapping("/customer/{client_id}")
    public ResponseEntity<?> fetchOffersByCustomer(@PathVariable("client_id") int client_id) {
        List<Offer> offers = offerService.getAllOffersForClient(client_id);

        if (offers.isEmpty()) {
            return AppResponse.error()
                    .withMessage("No offers found for the given customer")
                    .build();
        }

        return AppResponse.success()
                .withData(offers)
                .build();
    }

    // Листване на конкретна оферта по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchOfferById(@PathVariable("id") int id) {
        Offer offer = offerService.getOffer(id);

        if (offer == null) {
            return AppResponse.error()
                    .withMessage("Offer not found")
                    .build();
        }

        return AppResponse.success()
                .withData(offer)
                .build();
    }

    // Изтриване на оферта
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable("id") int id) {
        boolean deleted = offerService.deleteOffer(id);

        if (!deleted) {
            return AppResponse.error()
                    .withMessage("Offer could not be deleted")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Offer deleted successfully")
                .build();
    }

    // Приемане на оферта (актуализиране на офертата с приета статус)
    @PutMapping("/accept/{id}")
    public ResponseEntity<?> acceptOffer(@PathVariable("id") int id) {
        boolean accepted = offerService.acceptOffer(id);

        if (!accepted) {
            return AppResponse.error()
                    .withMessage("Offer could not be accepted")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Offer accepted successfully")
                .build();
    }
}

