package kz.iitu.itse1908.daniyal.repository;

import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NamedQuery;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface CarDealerRepository extends JpaRepository<CarDealer, Long> {

    @Override
    List<CarDealer> findAll();

    @Override
    Optional<CarDealer> findById(Long Long);

    @Override
    void delete(CarDealer carDealer);

    @Override
    <S extends CarDealer> S save(S entity);

    @Override
    <S extends CarDealer> List<S> saveAll(Iterable<S> entities);

    @Transactional
    @Modifying
    @Query("update #{#entityName} c set c.name = :name where c.id = :id")
    void updateCarDealer(@Param(value = "id") Long id, @Param(value = "name") String name);

    CarDealer getByIdNamed(Long id);

}
