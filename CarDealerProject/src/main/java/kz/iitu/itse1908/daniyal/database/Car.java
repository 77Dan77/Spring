package kz.iitu.itse1908.daniyal.database;

import kz.iitu.itse1908.daniyal.Validation.CarPriceConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import static javax.persistence.GenerationType.IDENTITY;

@Validated
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "car")
@NamedQueries({
        @NamedQuery(name="Car.getById",
                query="select distinct c from Car c left join fetch c.carDealer where c.id = :id", lockMode = LockModeType.READ),
        @NamedQuery(name="Car.getListOfCars",
                query="select distinct c from Car c left join fetch " +
                        "c.carDealer ca ", lockMode = LockModeType.NONE)
})
public class Car implements Serializable {

    private Long id;
    private String model;
    private int year;
    private int horsepower;
    private Double engineCapacity;
    private String carBody;
    private int appetite;
    private Long price;

    private CarDealer carDealer;

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Audited
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    @ManyToOne
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @JoinColumn(name = "car_dealer_id")
    @NotAudited
    @OrderBy("name")
    public CarDealer getCarDealer() {
        return carDealer;
    }

    @NotBlank(message = "Model is required")
    @Column(name = "model")
    public String getModel() {
        return model;
    }

    @Column(name = "year")
    public int getYear() {
        return year;
    }

    @Column(name = "horsepower")
    public int getHorsepower() {
        return horsepower;
    }

    @Column(name = "engine_capacity")
    public Double getEngineCapacity() {
        return engineCapacity;
    }

    @Column(name = "car_body")
    public String getCarBody() {
        return carBody;
    }

    @Column(name = "appetite")
    public int getAppetite() {
        return appetite;
    }

    //@CarPriceConstraint
    // @NotNull(message = "Price cannot be null")
    @Column(name = "price")
    public Long getPrice() {
        return price;
    }


    public static CarBuilder carBuilder() {
        return new Car().new CarBuilder();
    }

    public class CarBuilder {
        private CarBuilder() {
        }

        public CarBuilder setCarDealer(CarDealer carDealer) {
            Car.this.carDealer = carDealer;
            return this;
        }

        public CarBuilder setId(Long id) {
            Car.this.id = id;
            return this;
        }

        public CarBuilder setModel(String model) {
            Car.this.model = model;
            return this;
        }

        public CarBuilder setPrice(long price) {
            Car.this.price = price;
            return this;
        }

        public CarBuilder setAppetite(int appetite){
            Car.this.appetite = appetite;
            return this;
        }

        public CarBuilder setYear(int year) {
            Car.this.year = year;
            return this;
        }

        public CarBuilder setHorsepower(int horsepower) {
            Car.this.horsepower = horsepower;
            return this;
        }

        public CarBuilder setEngineCapacity(Double engineCapacity) {
            Car.this.engineCapacity = engineCapacity;
            return this;
        }

        public CarBuilder setCarBody(String carBody) {
            Car.this.carBody = carBody;
            return this;
        }

        public Car build() {
            return Car.this;
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setHorsepower(int horsepower) {
        this.horsepower = horsepower;
    }

    public void setEngineCapacity(Double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public void setCarBody(String carBody) {
        this.carBody = carBody;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setCarDealer(CarDealer carDealer) {
        this.carDealer = carDealer;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", horsepower=" + horsepower +
                ", engineCapacity=" + engineCapacity +
                ", carBody='" + carBody + '\'' +
                ", appetite=" + appetite +
                ", price=" + price + ", Car Dealer=" + carDealer;
    }
}
