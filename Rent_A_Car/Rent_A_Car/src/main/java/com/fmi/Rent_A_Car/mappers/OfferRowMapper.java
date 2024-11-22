package com.fmi.Rent_A_Car.mappers;

import com.fmi.Rent_A_Car.entities.Offer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OfferRowMapper implements RowMapper<Offer> {

    @Override
    public Offer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Offer offer = new Offer();
        offer.setId(rs.getInt("id"));
        offer.setClient_id(rs.getInt("client_id"));
        offer.setCar_id(rs.getInt("car_id"));
        offer.setStatus(rs.getString("status"));

        return offer;
    }
}
