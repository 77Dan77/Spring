package kz.iitu.itse1908.daniyal.database;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.GenerationType.IDENTITY;

@Validated
@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "car_dealer")
@NamedQueries({
        @NamedQuery(name="CarDealer.getByIdNamed",
                query="select distinct c from CarDealer c left join fetch c.cars where c.id = :id"),
        @NamedQuery(name="CarDealer.getListOfCars",
                query="select distinct c, ca from CarDealer c left join fetch " +
                        "c.cars ca ", lockMode = LockModeType.PESSIMISTIC_READ)
})
public class CarDealer {
    private Long id;
    private String name;
    private List<Car> cars = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Audited
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Cacheable
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @NotAudited
    @OneToMany(mappedBy = "carDealer", cascade = CascadeType.ALL,
            orphanRemoval=true, fetch = FetchType.EAGER)
    public List<Car> getCars() {
        return cars;
    }




    public static CarDealerBuilder carDealerBuilder() {
        return new CarDealer().new CarDealerBuilder();
    }

    public class CarDealerBuilder {
        private CarDealerBuilder() {
        }

        public CarDealerBuilder setId(Long id) {
            CarDealer.this.id = id;
            return this;
        }

        public CarDealerBuilder setName(String name) {
            CarDealer.this.name = name;
            return this;
        }

        public CarDealerBuilder setCars(List<Car> cars) {
            CarDealer.this.cars = cars;
            return this;
        }


        public CarDealer build() {
            return CarDealer.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String toString() {
        return "CarDealer{" +
                "id=" + id +
                ", name='" + name + '\'';
    }
}
