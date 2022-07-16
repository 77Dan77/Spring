//package kz.iitu.itse1908.daniyal.utils;
//
//import org.springframework.dao.DataAccessException;
//import org.springframework.jdbc.core.ResultSetExtractor;
//import org.springframework.stereotype.Component;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//import java.util.Map;
//
//@Component
//public class CarExtractor implements ResultSetExtractor<Map<String, List<Map<String, Long>>>> {
//    @Override
//    public Map<String, List<Map<String, Long>>> extractData(ResultSet rs) throws SQLException, DataAccessException {
//
//        Map<String, List<Map<String, Long>>> data = new LinkedHashMap<>();
//
//        while(rs.next()){
//            String dealer = rs.getString("name");
//            data.putIfAbsent(dealer, new ArrayList<>());
//            Map<String, Long> carMap = new LinkedHashMap<>();
//            String carModel = rs.getString("model");
//            Long carPrice = rs.getLong("price");
//            carMap.put(carModel, carPrice);
//            data.get(dealer).add(carMap);
//        }
////select car.model, car.price, car_dealer.name from car left join car_dealer on car.car_id = car_dealer.id order by car_dealer.name
//        return data;
//    }
//}
