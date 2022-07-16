package kz.iitu.itse1908.daniyal.controller;

import kz.iitu.itse1908.daniyal.PrepareData;
import kz.iitu.itse1908.daniyal.controller.customExeptions.ApiResponse;
import kz.iitu.itse1908.daniyal.controller.customExeptions.BlogapiException;
import kz.iitu.itse1908.daniyal.controller.customExeptions.MyResourceNotFoundException;
import kz.iitu.itse1908.daniyal.database.Car;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller("CarController")  //http://127.0.0.1:8080/swagger-ui.html
@EnableWebMvc
@RequestMapping(value = "/api/cars")
public class CarController {
    CarService carService;
    PrepareData prepareData;

    @Autowired
    public void setPrepareData(PrepareData prepareData) {
        this.prepareData = prepareData;
    }
    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ExceptionHandler(MyResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleExceptions(MyResourceNotFoundException exception) {
        return exception.getApiResponse();
    }
    List<Car> carsList;

    @PostConstruct
    public void init(){
        this.carsList = prepareData.carsList();
    }

    @GetMapping("/carPage")
    @ResponseStatus(HttpStatus.OK)
    public String carDealerPage(Model model){
        List<Car> cars = carsList;
        //List<Car> cars = carService.findAll();
        model.addAttribute("cars", cars);
        model.addAttribute("car", new Car());
        return "carPage";
    }

    @RequestMapping(value = "/carPage/addCarView", method = RequestMethod.POST)
    public String pageAddCarView(Model model) {
        model.addAttribute("car", new Car());
        return "addCarPage";
    }

    @RequestMapping(value = "/carPage/editCarView", method = RequestMethod.POST)
    public String pageEditCarView(@Valid @ModelAttribute("car")Car car, Model model) {
        model.addAttribute("car", car);
        return "addCarPage";
    }

    @RequestMapping(value = "/carPage/addCar", method = RequestMethod.POST)
    public RedirectView pageAddCar(@Valid @ModelAttribute("car")Car car, Model model) {
        this.carsList.add(car);
        return new RedirectView("../carPage");
    }

    @RequestMapping(value = "/carPage/editCar", method = RequestMethod.POST)
    public RedirectView pageEditCar(@Valid @ModelAttribute("car")Car car, Model model) {
        for(int i = 0; i < this.carsList.size(); i++){
            if (carsList.get(i).getId() == car.getId()){
                this.carsList.get(i).setId(car.getId());
                this.carsList.get(i).setModel(car.getModel());
                this.carsList.get(i).setCarBody(car.getCarBody());
                this.carsList.get(i).setAppetite(car.getAppetite());
                this.carsList.get(i).setHorsepower(car.getHorsepower());
                this.carsList.get(i).setYear(car.getYear());
                this.carsList.get(i).setEngineCapacity(car.getEngineCapacity());
                this.carsList.get(i).setPrice(car.getPrice());
                break;
            }
        }
        return new RedirectView("../carPage");
    }

    @RequestMapping(value = "/carPage/deleteCar", method = RequestMethod.POST)
    public RedirectView pageDeleteCar(@Valid @ModelAttribute("car")Car car, Model model, BindingResult result) {
        System.out.println("---"+car.getId());

        for(int i = 0; i < this.carsList.size(); i++){
            if (carsList.get(i).getId() == car.getId()){
                this.carsList.remove(i);
                break;
            }
        }
        return new RedirectView("../carPage");
    }


    //----------------------------------------------------------

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Car> getAllCars(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Car>> getCar(@PathVariable(name = "id") Long id) {
        carService.findById(id);
        return new ResponseEntity<>(carService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/addCar")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> addCar(@RequestBody Car car){ ///?
        carService.save(car);
        return new ResponseEntity<>(car, HttpStatus.CREATED);
    }

    @PutMapping("/updateCar/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Car> updateCar(@PathVariable(name = "id") Long id, @RequestBody Car car) {
        carService.updateCar(id, car.getModel(), car.getYear(), car.getHorsepower(), car.getEngineCapacity(),
                car.getCarBody(), car.getAppetite(), car.getPrice(), car.getCarDealer());
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCar/{id}")
    public String deleteCar(@PathVariable(name = "id") Long id, @RequestBody Car car) {
        car.setId(id);
        if (carService.findById(id).isEmpty()) throw new BlogapiException(HttpStatus.BAD_REQUEST, "There is no Car with this ID");
        else {
            carService.delete(car);
            return car.getModel() + " has been deleted!";
        }
    }
}
