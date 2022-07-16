package kz.iitu.itse1908.daniyal.controller;

import kz.iitu.itse1908.daniyal.PrepareData;
import kz.iitu.itse1908.daniyal.controller.customExeptions.ApiResponse;
import kz.iitu.itse1908.daniyal.controller.customExeptions.BlogapiException;
import kz.iitu.itse1908.daniyal.controller.customExeptions.MyResourceNotFoundException;
import kz.iitu.itse1908.daniyal.database.CarDealer;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.service.CarDealerService;
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

@Controller("CarDealerController")  //http://127.0.0.1:8080/swagger-ui.html
@EnableWebMvc
@RequestMapping(value = "/api/carDealers")
public class CarDealerController {
    CarDealerService carDealerService;
    PrepareData prepareData;
    @Autowired
    public void setPrepareData(PrepareData prepareData) {
        this.prepareData = prepareData;
    }
    @Autowired
    public CarDealerController(CarDealerService carDealerService) {
        this.carDealerService = carDealerService;
    }
    List<CarDealer> dealers;

    @PostConstruct
    public void init(){
        this.dealers = prepareData.carDealersList();
    }

    @ExceptionHandler(MyResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleExceptions(MyResourceNotFoundException exception) {
        return exception.getApiResponse();
    }

    @GetMapping("/carDealerPage")
    @ResponseStatus(HttpStatus.OK)
    public String carDealerPage(Model model){
        List<CarDealer> carDealers = dealers;
        model.addAttribute("carDealers", carDealers);
        model.addAttribute("carDealer", new CarDealer());
        return "carDealerPage";
    }

    @RequestMapping(value = "/carDealerPage/addCarDealerView", method = RequestMethod.POST)
    public String pageAddCarDealerView(Model model) {
        model.addAttribute("carDealer", new CarDealer());
        return "addCarDealerPage";
    }

    @RequestMapping(value = "/carDealerPage/editCarDealerView", method = RequestMethod.POST)
    public String pageEditCarDealerView(@Valid @ModelAttribute("carDealer")CarDealer carDealer, Model model) {
        model.addAttribute("carDealer", carDealer);
        return "addCarDealerPage";
    }

    @RequestMapping(value = "/carDealerPage/addCarDealer", method = RequestMethod.POST)
    public RedirectView pageAddCarDealer(@Valid @ModelAttribute("carDealer")CarDealer carDealer, Model model) {
        this.dealers.add(carDealer);
        return new RedirectView("../carDealerPage");
    }

    @RequestMapping(value = "/carDealerPage/editCarDealer", method = RequestMethod.POST)
    public RedirectView pageEditCarDealer(@Valid @ModelAttribute("carDealer")CarDealer carDealer, Model model) {
        for(int i = 0; i < this.dealers.size(); i++){
            if (dealers.get(i).getId() == carDealer.getId()){
                this.dealers.get(i).setId(carDealer.getId());
                this.dealers.get(i).setName(carDealer.getName());
                break;
            }
        }
        return new RedirectView("../carDealerPage");
    }

    @RequestMapping(value = "/carDealerPage/deleteCarDealer", method = RequestMethod.POST)
    public RedirectView pageDeleteCarDealer(@Valid @ModelAttribute("carDealer")CarDealer carDealer, Model model, BindingResult result) {
        System.out.println("---"+dealers);

        for(int i = 0; i < this.dealers.size(); i++){
            if (dealers.get(i).getId() == carDealer.getId()){
                this.dealers.remove(i);
                break;
            }
        }
        return new RedirectView("../carDealerPage");
    }


    //------------------------------------------------------------

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<CarDealer> getAllCarDealers(){
        return carDealerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<CarDealer>> getCarDealer(@PathVariable(name = "id") Long id) {
        carDealerService.findById(id);
        return new ResponseEntity<>(carDealerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/addCarDealer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDealer> addCarDealer(@RequestBody CarDealer carDealer){ ///?
        carDealerService.save(carDealer);
        return new ResponseEntity<>(carDealer, HttpStatus.CREATED);
    }

    @PutMapping("/updateCarDealer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<CarDealer> updateCarDealer(@PathVariable(name = "id") Long id, @RequestBody CarDealer carDealer) {
        carDealerService.updateCarDealer(id, carDealer.getName());
        return new ResponseEntity<>(carDealer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCarDealer/{id}")
    public String deleteCarDealer(@PathVariable(name = "id") Long id, @RequestBody CarDealer carDealer) {
        carDealer.setId(id);
        if (carDealerService.findById(id).isEmpty()) throw new BlogapiException(HttpStatus.BAD_REQUEST, "There is no CarDealer with this ID");
        else{
            carDealerService.delete(carDealer);
            return carDealer.getName() + " has been deleted!";
            }
        }

}
