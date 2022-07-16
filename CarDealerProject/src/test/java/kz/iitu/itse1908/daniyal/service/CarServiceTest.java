package kz.iitu.itse1908.daniyal.service;

import kz.iitu.itse1908.daniyal.repository.CarRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class CarServiceTest {
    @Mock
    CarRepository carRepository;


    @Test
    void create() {
        carRepository.save(any());
        verify(carRepository, times(1)).save(any());
    }

    @Test
    void getById(){carRepository.getById(Long.valueOf(1));
        verify(carRepository, times(1)).getById(Long.valueOf(1));
    }

    @Test
    void update(){
        carRepository.updateCar(any(),anyString(),any(),any(),any(),any(),any(),anyLong(),any());
        verify(carRepository, atMost(0)).updateCar(any(),anyString(),any(),any(),any(),any(),any(),anyLong(),any());
    }

    @Test
    void delete(){
        verify(carRepository, never()).deleteById(Long.valueOf(1));
    }
}