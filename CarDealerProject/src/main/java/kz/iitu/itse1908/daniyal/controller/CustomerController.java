package kz.iitu.itse1908.daniyal.controller;

import kz.iitu.itse1908.daniyal.controller.customExeptions.ApiResponse;
import kz.iitu.itse1908.daniyal.controller.customExeptions.BlogapiException;
import kz.iitu.itse1908.daniyal.controller.customExeptions.MyResourceNotFoundException;
import kz.iitu.itse1908.daniyal.database.Customer;
import kz.iitu.itse1908.daniyal.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.RedirectView;

import javax.swing.text.BadLocationException;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller("CustomerController")   //http://127.0.0.1:8080/swagger-ui.html
@EnableWebMvc
@RequestMapping(value = "/api/customers")
public class CustomerController {
    CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @ExceptionHandler(MyResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleExceptions(MyResourceNotFoundException exception) {
        return exception.getApiResponse();
    }

    @GetMapping("home")
    @ResponseStatus(HttpStatus.OK)
    public String home(Model model){
        return "homePage";
    }

    @GetMapping("/customerPage")
    @ResponseStatus(HttpStatus.OK)
    public String customerPage(Model model){
        List<Customer> customers = customerService.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer());
        return "customerPage";
    }

    @GetMapping("/error")
    @ResponseStatus(HttpStatus.OK)
    public String errorPage(Model model){
        return "errorPage";
    }

    @RequestMapping(value = "/customerPage/addCustomerView", method = RequestMethod.POST)
    public String pageAddCustomerView(Model model) {
        model.addAttribute("customer", new Customer());
        Customer customer = new Customer();
        System.out.println(customer.toString());
        return "addCustomerPage";
    }

    @RequestMapping(value = "/customerPage/editCustomerView", method = RequestMethod.POST)
    public String pageEditCustomerView(@Valid @ModelAttribute("customer")Customer customer, Model model) {
        model.addAttribute("customer", customer);
        System.out.println(customer.toString());
        return "addCustomerPage";
    }

    @RequestMapping(value = "/customerPage/addCustomer", method = RequestMethod.POST)
    public RedirectView pageAddCustomer(@Valid @ModelAttribute("customer")Customer customer, Model model) {
        customerService.save(customer);
        return new RedirectView("../customerPage");
    }

    @RequestMapping(value = "/customerPage/editCustomer", method = RequestMethod.POST)
    public RedirectView pageEditCustomer(@Valid @ModelAttribute("customer")Customer customer, Model model) {
        customerService.updateCustomer(customer.getId(), customer.getFname(), customer.getLname(), customer.getBalance());
        return new RedirectView("../customerPage");
    }

    @RequestMapping(value = "/customerPage/deleteCustomer", method = RequestMethod.POST, params = "delete")
    public RedirectView pageDeleteCustomer(@Valid @ModelAttribute("customer")Customer customer, Model model, BindingResult result) {
        customer.setId(customer.getId());
        if (customerService.findById(customer.getId()).isEmpty()) return new RedirectView("/error");
        else {
            customerService.delete(customer);
            return new RedirectView("../customerPage");
        }
    }

    //------------------------------------------------------------------

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Optional<Customer>> getCustomer(@PathVariable(name = "id") Long id) {
        customerService.findById(id);
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/addCustomer")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer){ ///?
        customerService.save(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);
    }

    @PutMapping("/updateCustomer/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Customer> updateCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer) {
        System.out.println(customer.toString());
        customerService.updateCustomer(id, customer.getFname(), customer.getLname(), customer.getBalance());
        return new ResponseEntity<>(customer, HttpStatus.OK);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public String deleteCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer) {
        customer.setId(id);
        if (customerService.findById(id).isEmpty()) throw new BlogapiException(HttpStatus.BAD_REQUEST, "There is no customer with this ID");
        else {
            customerService.delete(customer);
            return customer.getFname() + " has been deleted!";
        }
    }

}
