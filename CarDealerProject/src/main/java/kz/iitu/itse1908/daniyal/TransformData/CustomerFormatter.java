package kz.iitu.itse1908.daniyal.TransformData;

import kz.iitu.itse1908.daniyal.database.Customer;
import org.springframework.format.Formatter;

import java.text.ParseException;
import java.util.Locale;

public class CustomerFormatter implements Formatter<Customer> {

    @Override
    public Customer parse(String text, Locale locale) throws ParseException {
        if (text != null) {
            String[] parts = text.split(",");
            if (parts.length == 4) {
                Customer customer = new Customer();
                customer.setId(Long.parseLong(parts[0].trim()));
                customer.setFname(parts[1].trim());
                customer.setLname(parts[2].trim());
                customer.setBalance(Long.parseLong(parts[3].trim()));
                return customer;
            }
        }
        return null;
    }

    @Override
    public String print(Customer object, Locale locale) {
        if (object == null) {
            return "";
        }
        else{
            return String.format(locale, "%s, %s, %s", object.getId(), object.getFname(),
                object.getLname(), object.getBalance());
        }
    }
}
