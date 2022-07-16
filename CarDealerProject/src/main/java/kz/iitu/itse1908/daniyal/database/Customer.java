package kz.iitu.itse1908.daniyal.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.envers.Audited;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

import static javax.persistence.GenerationType.*;

@Validated
@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
//@Audited
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name="Customer.getById",
                query="select distinct c from Customer c where c.id = :id")
})
public class Customer {
    private Long id;
    private String fname;
    private String lname;
    private long balance;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Audited
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "first_name")
    public String getFname() {
        return fname;
    }

    @Column(name = "last_name")
    public String getLname() {
        return lname;
    }

    @Column(name = "balance")
    public long getBalance() {
        return balance;
    }

    public static CustomerBuilder CustomerBuilder() {
        return new Customer().new CustomerBuilder();
    }

    public class CustomerBuilder {
        private CustomerBuilder() {
        }

        public CustomerBuilder setId(Long id) {
            Customer.this.id = id;
            return this;
        }

        public CustomerBuilder setLname(String lname) {
            Customer.this.lname = lname;
            return this;
        }

        public CustomerBuilder setFname(String fname){
            Customer.this.fname = fname;
            return this;
        }

        public CustomerBuilder setBalance(long balance){
            Customer.this.balance = balance;
            return this;
        }

        public Customer build() {
            return Customer.this;
        }
    }

}
