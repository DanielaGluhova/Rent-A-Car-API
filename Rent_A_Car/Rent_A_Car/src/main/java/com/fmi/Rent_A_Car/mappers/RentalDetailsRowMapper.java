package com.fmi.Rent_A_Car.mappers;

import com.fmi.Rent_A_Car.entities.RentalDetails;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RentalDetailsRowMapper implements RowMapper<RentalDetails> {

    @Override
    public RentalDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        RentalDetails rentalDetails = new RentalDetails();
        rentalDetails.setId(rs.getInt("id"));
        rentalDetails.setRentalDays(rs.getInt("rental_days"));
        rentalDetails.setWeekendDays(rs.getInt("weekend_days"));

        return rentalDetails;
    }
}

