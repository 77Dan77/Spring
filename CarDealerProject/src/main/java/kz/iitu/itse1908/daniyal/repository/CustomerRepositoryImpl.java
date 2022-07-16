//package kz.iitu.itse1908.daniyal.repository;
//
//import kz.iitu.itse1908.daniyal.database.Customer;
//import kz.iitu.itse1908.daniyal.database.Car;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//
//@Repository
//@Transactional
//public class CustomerRepositoryImpl implements CustomerRepository, CRUDinterface<Customer> {
//    @Autowired
//    Customer customer;
//
//    SessionFactory sessionFactory;
//
//    @Autowired
//    public void setSessionFactory(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
////    @Autowired
////    public CustomerRepositoryImpl(JdbcTemplate jdbcTemplate) {
////        this.jdbcTemplate = jdbcTemplate;
////    }
//
//    @Override
//    public void buy(Car car) {
//        System.out.println("Car has been bought by a customer");
//
//    }
//
//    @Override
//    public Optional<Customer> getById(Long id) {
//            return Optional.of((Customer)sessionFactory.getCurrentSession().getNamedQuery("Customer.getById").
//                    setParameter("id", id).uniqueResult());
//    }
//
//    @Override
//    public Customer create(Customer customer) {
//        sessionFactory.getCurrentSession().saveOrUpdate(customer);
//        return customer;
//    }
//
//    @Override
//    public Customer update(Customer customer) {
//        sessionFactory.getCurrentSession().saveOrUpdate(customer);
//        return customer;
//    }
//
//    @Override
//    public void deleteById(Customer customer) {
//        System.out.println(customer.getFname() + " has been deleted");
//        sessionFactory.getCurrentSession().delete(customer);
//    }
//
//    @Override
//    @Transactional(propagation= Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
//    public List<Customer> getAll() {
//        return sessionFactory.getCurrentSession().createQuery("from Customer c").list();
//
//    }
//}
