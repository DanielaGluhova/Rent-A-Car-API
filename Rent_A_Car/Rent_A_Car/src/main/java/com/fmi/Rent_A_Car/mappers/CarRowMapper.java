package com.fmi.Rent_A_Car.mappers;



import com.fmi.Rent_A_Car.entities.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarRowMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        Car car = new Car();
        car.setId(rs.getInt("id"));
        car.setModel(rs.getString("model"));
        car.setCar_year(rs.getInt("car_year"));
        car.setDaily_rate(rs.getDouble("daily_rate"));
        car.setLocation(rs.getString("location"));
        car.setStatus(rs.getString("status"));

        return car;
    }
}

