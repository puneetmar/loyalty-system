package loyalty_system.loyaltysystemdemo.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import loyalty_system.loyaltysystemdemo.entity.Customer;
import loyalty_system.loyaltysystemdemo.repository.CustomerRepository;

@Component
public class LoyaltyPointsJob {

    @Autowired
    private CustomerRepository customerRepository;

    @Scheduled(fixedRate = 1000) // Run every second
    public void calculateLoyaltyPoints() {
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            double totalPurchaseAmount = customer.getTotalPurchaseAmount();
            int loyaltyPoints = (int) (totalPurchaseAmount / 100); // Adjust this logic as needed

            // Set loyalty points for the customer
            customer.setLoyaltyPoints(loyaltyPoints);

            // Save the updated customer
            customerRepository.save(customer);
        }
    }
}