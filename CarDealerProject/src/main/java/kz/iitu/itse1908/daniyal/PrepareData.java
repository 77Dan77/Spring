package kz.iitu.itse1908.daniyal;

import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.utils.CustomerItemProcessor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class PrepareData {
    @Autowired
    JdbcTemplate jdbcTemplate;
    CustomerItemProcessor customerProcessor;
    SessionFactory sessionFactory;
    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    @Autowired
    public void setCustomerProcessor(CustomerItemProcessor customerProcessor) {
        this.customerProcessor = customerProcessor;}

    public void createTables()  {
        log.info("Creating tables for testing...");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Customer");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Car");
        jdbcTemplate.execute("DROP TABLE IF EXISTS Car_dealer");

        jdbcTemplate.execute("CREATE TABLE Car_dealer(id SERIAL primary key, name VARCHAR(255))");
        jdbcTemplate.execute("CREATE TABLE Customer(id SERIAL primary key, first_name VARCHAR(255), last_name VARCHAR(255), balance NUMERIC(15))");
        String sql = "CREATE TABLE Car(id SERIAL primary key, model VARCHAR(255), year int, horsepower int," +
                " engine_capacity double precision, car_body VARCHAR(255), appetite int, price NUMERIC(15), " +
                "car_dealer_id int REFERENCES Car_dealer(id))";
        jdbcTemplate.execute(sql);
    }

    public List<CarDealer> carDealersList(){
        ArrayList<CarDealer> carDealers = new ArrayList<>();
        carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(1)).setName("Toyota").build());
        carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(2)).setName("Mercedes").build());
        carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(3)).setName("BMW").build());
        carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(4)).setName("Lexus").build());
        carDealers.add(CarDealer.carDealerBuilder().setId(Long.valueOf(5)).setName("Audi").build());
        return carDealers;
    }

    public List<Customer> customersList() throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();
        customers.add(customerProcessor.process(Customer.CustomerBuilder().
                setFname("Daniyal").setLname("Zhexenov").setBalance(Long.valueOf(50000)).build()));
        customers.add(customerProcessor.process(Customer.CustomerBuilder().
                setFname("Andrei").setLname("Volkov").setBalance(Long.valueOf(40000)).build()));
        customers.add(customerProcessor.process(Customer.CustomerBuilder().
                setFname("John").setLname("Uik").setBalance(Long.valueOf(100000)).build()));
        customers.add(customerProcessor.process(Customer.CustomerBuilder().
                setFname("Muhammad").setLname("Ahdi").setBalance(Long.valueOf(60000)).build()));
        customers.add(customerProcessor.process(Customer.CustomerBuilder().
                setFname("Max").setLname("Payne").setBalance(Long.valueOf(1000000)).build()));
        return customers;
    }

    public List<Car> carsList(){
        ArrayList<Car> cars = new ArrayList<>();
        cars.add(Car.carBuilder().setId(Long.valueOf(1)).setModel("CLS55 AMG").
                setYear(2006).setEngineCapacity(5.5).setHorsepower(445).setAppetite(25).
                setCarBody("sedan").setPrice(9000000).setCarDealer(carDealersList().get(1)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(2)).setModel("E63 AMG").
                setYear(2013).setEngineCapacity(6.2).setHorsepower(625).setAppetite(26).
                setCarBody("sedan").setPrice(19000000).setCarDealer(carDealersList().get(1)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(3)).setModel("BMW M6").
                setYear(2007).setEngineCapacity(5.0).setHorsepower(507).setAppetite(24).
                setCarBody("coupe").setPrice(9500000).setCarDealer(carDealersList().get(2)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(4)).setModel("BMW X5M").
                setYear(2012).setEngineCapacity(4.4).setHorsepower(512).setAppetite(22).
                setCarBody("crossover").setPrice(20000000).setCarDealer(carDealersList().get(2)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(5)).setModel("Lexus IS F").
                setYear(2010).setEngineCapacity(5.0).setHorsepower(475).setAppetite(23).
                setCarBody("sedan").setPrice(15000000).setCarDealer(carDealersList().get(3)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(6)).setModel("Lexus LFA").
                setYear(2012).setEngineCapacity(5.0).setHorsepower(560).setAppetite(26).
                setCarBody("sport coupe").setPrice(15000000).setCarDealer(carDealersList().get(3)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(7)).setModel("Audi RS6").
                setYear(2003).setEngineCapacity(4.2).setHorsepower(385).setAppetite(23).
                setCarBody("univesal").setPrice(8000000).setCarDealer(carDealersList().get(4)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(8)).setModel("Audi R8").
                setYear(2016).setEngineCapacity(4.8).setHorsepower(575).setAppetite(20).
                setCarBody("sport coupe").setPrice(3500000).setCarDealer(carDealersList().get(4)).build());
        cars.add(Car.carBuilder().setId(Long.valueOf(9)).setModel("Toyota Supra").
                setYear(1995).setEngineCapacity(3.0).setHorsepower(280).setAppetite(21).
                setCarBody("coupe").setPrice(1000000).setCarDealer(carDealersList().get(0)).build());
        return cars;
    }

}
