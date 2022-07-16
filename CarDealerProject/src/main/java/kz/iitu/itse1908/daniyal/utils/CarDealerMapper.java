//package kz.iitu.itse1908.daniyal.utils;
//
//import kz.iitu.itse1908.daniyal.database.CarDealer;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component
//public class CarDealerMapper implements RowMapper<CarDealer> {
//    @Override
//    public CarDealer mapRow(ResultSet rs, int rowNum) throws SQLException {
//        CarDealer carDealer = new CarDealer();
//        carDealer.setId(rs.getLong("id"));
//        carDealer.setName(rs.getString("name"));
//        return carDealer;
//    }
//}
