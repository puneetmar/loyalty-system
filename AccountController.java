package loyalty_system.loyaltysystemdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loyalty_system.loyaltysystemdemo.entity.Customer;
import loyalty_system.loyaltysystemdemo.repository.CustomerRepository;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getAccountInfo(@PathVariable Long customerId) {
        // Find the customer by ID
        Customer customer = customerRepository.findById(customerId).orElse(null);

        if (customer != null) {
            // Calculate tier and loyalty points if needed
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}



