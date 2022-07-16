package kz.iitu.itse1908.daniyal.TransformData;

import kz.iitu.itse1908.daniyal.database.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StrToCustomerConverter implements Converter<String, Customer> {

    @Override
    public Customer convert(String source) {
        String[] data = source.split(",");
        return Customer.CustomerBuilder().setId(Long.parseLong(data[0])).
                setFname(data[1]).setLname(data[2]).setBalance(Long.parseLong(data[3])).build();
    }
}
