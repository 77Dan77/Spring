package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    @Test
    public void setFname() {
        Customer customer = new Customer();
        ReflectionTestUtils.setField(customer, "fname", "dan");
        assertTrue(customer.getFname().equals("dan"));
    }

    @Test
    void create() {
        customerRepository.save(any());
        verify(customerRepository, times(1)).save(any());
    }

    @Test
    void getById(){customerRepository.getById(Long.valueOf(1));
        verify(customerRepository, times(1)).getById(Long.valueOf(1));
    }

    @Test
    void update(){
        customerRepository.updateCustomer(any(),anyString(),anyString(),anyLong());
        verify(customerRepository, atMost(1)).updateCustomer(any(),anyString(),anyString(),anyLong());
    }

    @Test
    void delete(){
        verify(customerRepository, never()).deleteById(Long.valueOf(1));
    }

}