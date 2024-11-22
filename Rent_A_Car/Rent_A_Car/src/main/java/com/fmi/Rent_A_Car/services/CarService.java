package com.fmi.Rent_A_Car.services;

import com.fmi.Rent_A_Car.entities.Car;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarService {

    private final JdbcTemplate db;

    public CarService(JdbcTemplate db) {
        this.db = db;
    }

    // Добавяне на нов автомобил
    public boolean createCar(Car car) {
        String query = "INSERT INTO cars (model, car_year, daily_rate, location, status, is_deleted) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        db.update(query, car.getModel(), car.getCar_year(), car.getDaily_rate(),
                car.getLocation(), car.getStatus(), car.getIs_deleted());
        return true;
    }

    // Листване на всички автомобили, на базата на локацията на клиента
    public List<Car> getCarsByLocation(String location) {
        String query = "SELECT * FROM cars WHERE location = ? AND is_deleted = 0";
        return db.query(query, new Object[]{location},
                (rs, rowNum) -> new Car(rs.getInt("id"), rs.getString("model"), rs.getInt("car_year"),
                        rs.getDouble("daily_rate"), rs.getString("location"), rs.getString("status"),
                        rs.getInt("is_deleted")));
    }

    // Листване на конкретен автомобил по ID
    public Car getCarById(int id) {
        String query = "SELECT * FROM cars WHERE id = ? AND is_deleted = 0";
        List<Car> cars = db.query(query, new Object[]{id},
                (rs, rowNum) -> new Car(rs.getInt("id"), rs.getString("model"), rs.getInt("car_year"),
                        rs.getDouble("daily_rate"), rs.getString("location"), rs.getString("status"),
                        rs.getInt("is_deleted")));
        if (cars.isEmpty()) {
            return null;
        }
        return cars.get(0);
    }

    // Актуализация на съществуващ автомобил
    public boolean updateCar(Car car) {
        String query = "UPDATE cars SET model = ?, car_year = ?, daily_rate = ?, location = ?, status = ? " +
                "WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, car.getModel(), car.getCar_year(), car.getDaily_rate(),
                car.getLocation(), car.getStatus(), car.getId());
        return resultCount == 1;
    }

    // Изтриване на автомобил от системата
    public boolean deleteCar(int id) {
        String query = "UPDATE cars SET is_deleted = 1 WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, id);
        return resultCount == 1;
    }
}
