package kz.iitu.itse1908.daniyal.repository;

import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Override
    List<Customer> findAll();

    @Override
    Optional<Customer> findById(Long t);

    @Override
    void delete(Customer customer);

    @Override
    <S extends Customer> S save(S entity);

    @Override
    <S extends Customer> List<S> saveAll(Iterable<S> entities);

    @Transactional
    @Modifying
    @Query("update #{#entityName} c set c.fname = :fname, c.lname = :lname, c.balance = :balance where c.id = :id")
    void updateCustomer(@Param(value = "id") Long id, @Param(value = "fname") String fname, @Param(value = "lname") String lname,
                         @Param(value = "balance") long balance);

}
