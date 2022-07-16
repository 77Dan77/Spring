package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.Scheduling.ScheduledTasks;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.repository.CarDealerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Validated
@Service
public class CarDealerService {
    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
    CarDealerRepository carDealerRepository;
    @Autowired
    public CarDealerService(CarDealerRepository carDealerRepository) {
        this.carDealerRepository = carDealerRepository;
    }

    public List<CarDealer> findAll(){
        return carDealerRepository.findAll();
    }

    public Optional<CarDealer> findById(Long id){
        return carDealerRepository.findById(id);
    }

    public void delete(CarDealer carDealer){
        carDealerRepository.delete(carDealer);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends CarDealer> S save(S entity){
        return carDealerRepository.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public <S extends CarDealer> List<S> saveAll(Iterable<S> entities){
        return carDealerRepository.saveAll(entities);
    }

    public void updateCarDealer(Long id, String name){
        carDealerRepository.updateCarDealer(id, name);
    }

//    @Scheduled(fixedRate=2000)
//    public void sayHello() {
//        for(int i=1 ; i<=5 ; i++) {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.info("Hello from CarDealer " + i);
//        }
//    }

}
