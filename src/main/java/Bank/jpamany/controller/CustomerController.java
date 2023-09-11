package Bank.jpamany.controller;

import Bank.jpamany.entity.Address;
import Bank.jpamany.entity.Customer;
import Bank.jpamany.service.AddressService;
import Bank.jpamany.service.CustomerService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@Validated
public class CustomerController {
    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public List<Customer> getAllCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerByCustomerId(@Positive @PathVariable int id) {
        return customerService.find(id);
    }

    @PostMapping("/")
    public Customer addCustomer(@RequestBody Customer customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
        Customer customerToUpdate = getCustomerByCustomerId(id);
        if (customerToUpdate != null) {
            customer.setId(id);
            return customerService.save(customer);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Customer deleteCustomer(@PathVariable int id) {
        Customer customerToDelete = getCustomerByCustomerId(id);
        customerService.delete(customerToDelete);
        return customerToDelete;
    }

}
