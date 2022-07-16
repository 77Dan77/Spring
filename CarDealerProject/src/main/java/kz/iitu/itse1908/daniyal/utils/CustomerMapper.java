//package kz.iitu.itse1908.daniyal.utils;
//
//import kz.iitu.itse1908.daniyal.database.Customer;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//
//import javax.swing.tree.TreePath;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//@Component
//public class CustomerMapper implements RowMapper<Customer> {
//    @Override
//    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Customer customer = new Customer();
//        customer.setId(rs.getLong("id"));
//        customer.setFname(rs.getString("fname"));
//        customer.setLname(rs.getString("lname"));
//        customer.setBalance(rs.getLong("balance"));
//        return customer;
//    }
//}
