//package kz.iitu.itse1908.daniyal.repository;
//
//import kz.iitu.itse1908.daniyal.database.CarDealer;
//import kz.iitu.itse1908.daniyal.database.Customer;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.OrderBy;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//
//
//@Repository
//@Transactional
//public class CarDealerRepositoryImpl implements CarDealerRepository{
//
//    SessionFactory sessionFactory;
//
//    @Autowired
//    public CarDealerRepositoryImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//
//    @Override
//    public List<CarDealer> findAll() {
//        return null;
//    }
//
//    @Override
//    public Optional<CarDealer> findById(Long aLong) {
//        return Optional.empty();
//    }
//
//    @Override
//    public void delete(CarDealer carDealer) {
//
//    }
//
//    @Override
//    public <S extends CarDealer> S save(S entity) {
//        return null;
//    }
//
//    @Override
//    public <S extends CarDealer> List<S> saveAll(Iterable<S> entities) {
//        return null;
//    }
//}
