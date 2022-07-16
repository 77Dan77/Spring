package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.Scheduling.ScheduledTasks;
import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.repository.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    private CarRepository carRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAll(){
        return carRepository.findAll();
    }

    public Optional<Car> findById(Long id){
        return carRepository.findById(id);
    }

    public void delete(Car car){
        carRepository.delete(car);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends Car> S save(S entity){
        return carRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends Car> List<S> saveAll(Iterable<S> entities){
        return carRepository.saveAll(entities);
    }

    public List<Car> getListOfCars(){
        return carRepository.getListOfCars();
    }

    public void updateCar(Long id, String model, int year, int horsepower, Double engineCapacity, String carBody,
                          int appetite, Long price, CarDealer carDealer){
        carRepository.updateCar(id, model, year, horsepower, engineCapacity, carBody, appetite, price, carDealer);
    }

//    @Scheduled(fixedRate=2000)
//    public void sayHello() {
//        for(int i=1 ; i<=5 ; i++) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("Hello from Car " + i);
//        }
//    }
}
