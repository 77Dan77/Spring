package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.repository.CarDealerRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class CarDealerServiceTest {

    @Mock
    CarDealerRepository carDealerRepository;

    @Test
    void getListOfCars() {
        List<String> cars = mock(List.class);
        cars.size();
        cars.add("Toyota Camry");
        cars.clear();

        InOrder inOrder = Mockito.inOrder(cars);
        inOrder.verify(cars).size();
        inOrder.verify(cars).add("Toyota Camry");
        inOrder.verify(cars).clear();
    }

    @Test
    void createCar() {
        carDealerRepository.save(any());
        verify(carDealerRepository, times(1)).save(any());
    }

    @Test
    void getById(){
        carDealerRepository.getById(Long.valueOf(1));
        verify(carDealerRepository, times(1)).getById(Long.valueOf(1));
    }

    @Test
    void update(){
        carDealerRepository.updateCarDealer(any(), any());
        verify(carDealerRepository, atMost(1)).updateCarDealer(any(), any());
    }

    @Test
    void delete(){
        verify(carDealerRepository, never()).deleteById(any());
    }


}