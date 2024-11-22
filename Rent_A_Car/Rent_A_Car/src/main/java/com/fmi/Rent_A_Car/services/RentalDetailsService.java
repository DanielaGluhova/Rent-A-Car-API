package com.fmi.Rent_A_Car.services;

import com.fmi.Rent_A_Car.entities.Car;
import com.fmi.Rent_A_Car.entities.Client;
import com.fmi.Rent_A_Car.entities.RentalDetails;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.List;

@Service
public class RentalDetailsService {

    private final JdbcTemplate db;

    public RentalDetailsService(JdbcTemplate db) {
        this.db = db;
    }

    // Добавяне на нови детайли за наем
    // Добавяне на нови детайли за наем
    public int addRentalDetails(RentalDetails rentalDetails) {
        String query = "INSERT INTO rental_details (rental_days, weekend_days) VALUES (?, ?)";
        final int[] id = new int[1];
        db.update(con -> {
            PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, rentalDetails.getRentalDays());
            ps.setInt(2, rentalDetails.getWeekendDays());

            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                // Retrieve the auto-generated keys (insert ID)
                ResultSet generatedKeys = ps.getGeneratedKeys();
                generatedKeys.next();
                id[0] = generatedKeys.getInt(1);
            }

            return ps;
        });
        
        return id[0];
    }

    // Актуализация на детайли за наем
    public boolean updateRentalDetails(RentalDetails rentalDetails) {
        String query = "UPDATE rental_details SET rental_days = ?, weekend_days = ? WHERE id = ?";
        int resultCount = db.update(query, rentalDetails.getRentalDays(), rentalDetails.getWeekendDays(), rentalDetails.getId());
        return resultCount == 1;
    }

    // Листване на детайли за наем по ID
    public RentalDetails getRentalDetailsById(int id) {
        String query = "SELECT * FROM rental_details WHERE id = ?";
        List<RentalDetails> rentalDetails = db.query(query, new Object[]{id},
                (rs, rowNum) -> new RentalDetails(
                        rs.getInt("id"), rs.getInt("rental_days"),
                        rs.getInt("weekend_days")));
        if (rentalDetails.isEmpty()) {
            return null;
        }
        return rentalDetails.get(0);
    }

    //Листване на всички наемни детайли
    public List<RentalDetails> getAllRentalDetails() {
        String query = "SELECT * FROM rental_details WHERE is_deleted = 0";
        return db.query(query, (rs, rowNum) -> new RentalDetails(
                rs.getInt("id"),
                rs.getInt("rentalDays"),
                rs.getInt("weekendDays")
        ));
    }
}
