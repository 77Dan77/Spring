package kz.iitu.itse1908.daniyal.utils;

import kz.iitu.itse1908.daniyal.database.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class CustomerItemProcessor implements ItemProcessor<Customer, Customer> {

    private static final Logger log = LoggerFactory.getLogger(CustomerItemProcessor.class);

    @Override
    public Customer process(final Customer customer) throws Exception {
        final String firstName = customer.getFname().toUpperCase();
        final String lastName = customer.getLname().toUpperCase();

        final Customer transformedPerson =  Customer.CustomerBuilder().
                setFname(firstName).setLname(lastName).setId(customer.getId()).
                setBalance(customer.getBalance()).build();

        log.info("Converting (" + customer + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }
}
