package loyalty_system.loyaltysystemdemo.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import loyalty_system.loyaltysystemdemo.entity.Customer;
import loyalty_system.loyaltysystemdemo.entity.Purchase;
import loyalty_system.loyaltysystemdemo.entity.PurchaseRequest;
import loyalty_system.loyaltysystemdemo.repository.CustomerRepository;
import loyalty_system.loyaltysystemdemo.repository.PurchaseRepository;

@RestController
@RequestMapping("/api/v1/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping
    public ResponseEntity<String> purchase(@RequestBody PurchaseRequest request) {
        // Create a new Purchase record
        Purchase purchase = new Purchase();
        purchase.setAmount(request.getAmount());
        purchase.setPurchaseDate(LocalDateTime.now());

        // Find the customer by ID (you should validate customer existence)
        Customer customer = customerRepository.findById(request.getCustomerId()).orElse(null);

        if (customer != null) {
            // Update customer's total purchase amount
            customer.setTotalPurchaseAmount(customer.getTotalPurchaseAmount() + request.getAmount());

            // Calculate and set the customer's tier
            if (customer.getTotalPurchaseAmount() < 5000) {
                customer.setTier("Silver");
            } else if (customer.getTotalPurchaseAmount() >= 5000 && customer.getTotalPurchaseAmount() <= 10000) {
                customer.setTier("Golden");
            } else {
                customer.setTier("Diamond");
            }

            // Save the updated customer and purchase
            customerRepository.save(customer);
            purchase.setCustomer(customer);
            purchaseRepository.save(purchase);

            return ResponseEntity.ok("Purchase successful");
        } else {
            return ResponseEntity.badRequest().body("Customer not found");
        }
    }

    @GetMapping
    public ResponseEntity<List<Purchase>> getAllPurchases() {
        List<Purchase> purchases = purchaseRepository.findAll();
        return ResponseEntity.ok(purchases);
    }
}
