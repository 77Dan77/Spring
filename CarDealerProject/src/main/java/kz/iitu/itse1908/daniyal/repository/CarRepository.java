package kz.iitu.itse1908.daniyal.repository;

import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Override
    List<Car> findAll();

    @Override
    Optional<Car> findById(Long t);

    @Override
    void delete(Car car);

    @Override
    <S extends Car> S save(S entity);

    @Override
    <S extends Car> List<S> saveAll(Iterable<S> entities);

    @Transactional
    @Modifying
    @Query("update #{#entityName} c set c.model = :model, c.year = :year, c.horsepower = :horsepower, c.engineCapacity = :engineCapacity" +
            ", c.carBody = :carBody, c.appetite = :appetite, c.price = :price, c.carDealer = :carDealer where c.id = :id")
    void updateCar(@Param(value = "id") Long id, @Param(value = "model") String model, @Param(value = "year") int year,
                         @Param(value = "horsepower") int horsepower, @Param(value = "engineCapacity") Double engineCapacity,
                         @Param(value = "carBody") String carBody, @Param(value = "appetite") int appetite,
                         @Param(value = "price") Long price, @Param(value = "carDealer") CarDealer carDealer);

    List<Car> getListOfCars();

}
