package com.fmi.Rent_A_Car.mappers;


import com.fmi.Rent_A_Car.entities.Client;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientRowMapper implements RowMapper<Client> {

    @Override
    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
        Client client = new Client();
        client.setId(rs.getInt("id"));
        client.setName(rs.getString("name"));
        client.setAddress(rs.getString("address"));
        client.setPhone(rs.getString("phone"));
        client.setAge(rs.getInt("age"));
        client.setHas_incidents(rs.getInt("has_incidents"));
        client.setIs_deleted(rs.getInt("is_deleted"));

        return client;
    }
}

