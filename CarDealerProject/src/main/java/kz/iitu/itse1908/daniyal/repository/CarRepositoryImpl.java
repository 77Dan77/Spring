//package kz.iitu.itse1908.daniyal.repository;
//
//import kz.iitu.itse1908.daniyal.config.TrackTime;
//import kz.iitu.itse1908.daniyal.database.Car;
//import kz.iitu.itse1908.daniyal.database.CarDealer;
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
//public class CarRepositoryImpl implements CarRepository, CRUDinterface<Car> {
//
//    SessionFactory sessionFactory;
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public CarRepositoryImpl() {
//
//    }
//
//    @OrderBy("model ASC")
//    public List<Car> getGroupedCars() {
//        return sessionFactory.getCurrentSession().getNamedQuery("Car.getListOfCars").list();
//    }
//
//
//    @Override
//    public void move(String model) {
//        System.out.println(model + " is moving");
//    }
//
//    @Override
//    @TrackTime
//    public Double calcFuelConsump(int appetite, long pathLength) {
//        double consuption = ((double)appetite / 100) * pathLength;
//        return consuption;
//    }
//
//    @Override
//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
//    public Optional<Car> getById(Long id) {
//        return Optional.of((Car)sessionFactory.getCurrentSession().getNamedQuery("Car.getById").
//                setParameter("id", id).uniqueResult());
//    }
//
//    @Override
//    public Car create(Car car) {
//        sessionFactory.getCurrentSession().saveOrUpdate(car);
//        return car;
//    }
//
//    @Override
//    public Car update(Car car) {
//        sessionFactory.getCurrentSession().saveOrUpdate(car);
//        return car;
//    }
//
//    @Override
//    public void deleteById(Car car) {
//        sessionFactory.getCurrentSession().delete(car);
//    }
//
//    @Override
//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
//    public List<Car> getAll() {
//        return sessionFactory.getCurrentSession().createQuery("from Car c ").list();
//    }
//}
