package kz.iitu.itse1908.daniyal.utils;

import kz.iitu.itse1908.daniyal.database.Car;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

//@Component
//public class CarMapper implements RowMapper<Car> {
//    @Override
//    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Car car = new Car();
//        car.setId(rs.getLong("id"));
//        car.setModel(rs.getString("model"));
//        car.setYear(rs.getInt("year"));
//        car.setHorsepower(rs.getInt("horsepower"));
//        car.setEngineCapacity(rs.getDouble("engine_capacity"));
//        car.setCarBody(rs.getString("car_body"));
//        car.setAppetite(rs.getInt("appetite"));
//        car.setPrice(rs.getLong("price"));
//        car.setCarDealer_id(rs.getLong("car_id"));
//        return car;
//    }
//}
