package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.Scheduling.ScheduledTasks;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    CustomerRepository customerRepository;
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    public Optional<Customer> findById(Long id){
        return customerRepository.findById(id);
    }

    public void delete(Customer customer){
        customerRepository.delete(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends Customer> S save(S entity){
        return customerRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends Customer> List<S> saveAll(Iterable<S> entities){
        return customerRepository.saveAll(entities);
    }

    public void updateCustomer(Long id, String fname, String lname, long balance){
        customerRepository.updateCustomer(id, fname, lname, balance);
    }

//    @Scheduled(fixedRate=2000)
//    public void sayHello() {
//        for(int i=1 ; i<=5 ; i++) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("Hello from Customer " + i);
//        }
//    }
}
