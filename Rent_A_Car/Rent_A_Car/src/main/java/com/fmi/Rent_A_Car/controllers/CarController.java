package com.fmi.Rent_A_Car.controllers;

import com.fmi.Rent_A_Car.entities.Car;
import com.fmi.Rent_A_Car.http.AppResponse;
import com.fmi.Rent_A_Car.services.CarService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    // Създаване на нов автомобил
    @PostMapping
    public ResponseEntity<?> createCar(@RequestBody Car car) {
        if (carService.createCar(car)) {
            return AppResponse.success()
                    .withMessage("Car created successfully")
                    .build();
        }
        return AppResponse.error()
                .withMessage("Car could not be created")
                .build();
    }

    // Листване на всички автомобили
    @GetMapping
    public ResponseEntity<?> fetchAllCars(@RequestParam("location") String location) {
        List<Car> cars = carService.getCarsByLocation(location);

        if (cars.isEmpty()) {
            return AppResponse.error()
                    .withMessage("No cars available for the selected location")
                    .build();
        }

        return AppResponse.success()
                .withData(cars)
                .build();
    }

    // Листване на конкретен автомобил по ID
    @GetMapping("/{id}")
    public ResponseEntity<?> fetchCarById(@PathVariable("id") int id) {
        Car car = carService.getCarById(id);

        if (car == null) {
            return AppResponse.error()
                    .withMessage("Car not found")
                    .build();
        }

        return AppResponse.success()
                .withData(car)
                .build();
    }

    // Актуализиране на автомобил
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCar(@RequestBody Car car, @PathVariable("id") int id) {
        Car carToUpdate = carService.getCarById(id);
        carToUpdate.setModel(car.getModel());
        carToUpdate.setCar_year(car.getCar_year());
        carToUpdate.setDaily_rate(car.getDaily_rate());
        carToUpdate.setStatus(car.getStatus());
        carToUpdate.setLocation(car.getLocation());
        boolean updated = carService.updateCar(carToUpdate);

        if (!updated) {
            return AppResponse.error()
                    .withMessage("Car could not be updated")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Car updated successfully")
                .build();
    }

    // Изтриване на автомобил
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCar(@PathVariable("id") int id) {
        boolean deleted = carService.deleteCar(id);

        if (!deleted) {
            return AppResponse.error()
                    .withMessage("Car could not be deleted")
                    .build();
        }

        return AppResponse.success()
                .withMessage("Car deleted successfully")
                .build();
    }
}
