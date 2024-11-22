package com.fmi.Rent_A_Car.services;

import com.fmi.Rent_A_Car.entities.Offer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OfferService {

    private final JdbcTemplate db;

    public OfferService(JdbcTemplate db) {
        this.db = db;
    }

    // създаване на нова оферта с данни за потребителя, модела на автомобила и дните за наемане
    public boolean createOffer(Offer offer) {
        String query = "INSERT INTO offers (client_id, car_id, rental_details_id) " +
                "VALUES (?, ?, ?)";
        db.update(query, offer.getClient_id(), offer.getCar_id(), offer.getRental_details_id());
        return true;
    }

    // листване на всички оферти за даден потребител
    public List<Offer> getAllOffersForClient(int clientId) {
        String query = "SELECT * FROM offers WHERE client_id = ? AND is_deleted = 0";
        return db.query(query, new Object[]{clientId},
                (rs, rowNum) -> new Offer(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("car_id"),rs.getInt("rental_details_id"),
                        rs.getString("status"), rs.getInt("is_deleted")));
    }

    // листване на конкретна оферта
    public Offer getOffer(int id) {
        String query = "SELECT * FROM offers WHERE id = ? AND is_deleted = 0";
        List<Offer> offers = db.query(query, new Object[]{id},
                (rs, rowNum) -> new Offer(rs.getInt("id"), rs.getInt("client_id"), rs.getInt("car_id"),rs.getInt("rental_details_id"),
                        rs.getString("status"), rs.getInt("is_deleted")));
        if (offers.isEmpty()) {
            return null;
        }
        return offers.get(0);
    }

    // изтриване на оферта
    public boolean deleteOffer(int id) {
        String query = "UPDATE offers SET is_deleted = 1 WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, id);
        return resultCount == 1;
    }

    // приемане на оферта - в склучай в които потребителя вземе автомобила
    public boolean acceptOffer(int id) {
        String query = "UPDATE offers SET status = 'accepted' WHERE id = ? AND is_deleted = 0";
        int resultCount = db.update(query, id);
        return resultCount == 1;
    }
}