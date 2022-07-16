package kz.iitu.itse1908.daniyal;

import kz.iitu.itse1908.daniyal.TransformData.StrToCustomerConverter;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.service.CarDealerService;
import kz.iitu.itse1908.daniyal.service.CarService;
import kz.iitu.itse1908.daniyal.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.convert.ConversionService;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;


@Component
@EnableScheduling
@EnableAspectJAutoProxy
@Slf4j
public class MainRunner implements CommandLineRunner {

    CarDealerService carDealerService;
    CustomerService customerService;
    CarService carService;
    PrepareData prepareData;
    ConversionService conversionService;
    StrToCustomerConverter strToCustomerConverter;

    @Autowired
    public void setStrToCustomerConverter(StrToCustomerConverter strToCustomerConverter) {
        this.strToCustomerConverter = strToCustomerConverter;
    }
    @Autowired
    public void setConversionService(ConversionService conversionService) {
        this.conversionService = conversionService;
    }
    @Autowired
    public MainRunner(PrepareData prepareData) {this.prepareData = prepareData;}
    @Autowired
    public void setCarDealerService(CarDealerService carDealerService) {
        this.carDealerService = carDealerService;
    }
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }
    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("Creating tables...");
        prepareData.createTables();

        log.info("Inserting data...");
        //carDealerService.saveAll(prepareData.carDealersList());
        customerService.saveAll(prepareData.customersList());
        //carService.saveAll(prepareData.carsList());

        System.out.println("--------------FindAll:");
        //System.out.println(carDealerService.findAll());
        //System.out.println(carService.findAll());
        System.out.println(customerService.findAll());

        System.out.println("--------------Update:");
        carDealerService.updateCarDealer(Long.valueOf(1), "Honda");

        System.out.println("--------------FindByID:");
        System.out.println(carDealerService.findById(Long.valueOf(2)));
        System.out.println(carService.findById(Long.valueOf(3)));
        System.out.println(customerService.findById(Long.valueOf(1)));

//        System.out.println("--------------Delete:");
//        carService.delete(cars.get(2));
//        carService.delete(cars.get(3));
//        carDealerService.delete(carDealers.get(2));
//        customerService.delete(customers.get(3));

//        int size = CacheManager.ALL_CACHE_MANAGERS.size();
//        System.out.println("-------------------------------" + size + "-----------------------------------");

//        log.info("Groupped car list: ");
//        System.out.println(carService.getListOfCars() + "\n\n");
//
//        Customer customer1 = Customer.CustomerBuilder().setId(Long.valueOf(1)).setFname("Dan").setLname("Zhexenov").setBalance(1000).build();
//        Customer customer2 = strToCustomerConverter.convert("1,Dan,Zhexenov,1000");
////       Customer customer2 = conversionService.convert("1,Dan,Zhexenov,1000", Customer.class);
//        System.out.println(customer1);
//        System.out.println("Converted: " + customer2);

//        AuditReader reader = AuditReaderFactory.get(sessionFactory.openSession());
//        AuditQuery queryCustomer = reader.createQuery()
//                .forRevisionsOfEntity(Customer.class, true, true);
//        System.out.println(queryCustomer.toString());
    }
}
