package loyalty_system.loyaltysystemdemo.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;
import loyalty_system.loyaltysystemdemo.entity.Customer;
import loyalty_system.loyaltysystemdemo.repository.CustomerRepository;

@Component
public class TierManagementJob {

    @Autowired
    private CustomerRepository customerRepository;

    @Scheduled(fixedRate = 60000) // Run every minute
    public void manageTiers() {
        List<Customer> customers = customerRepository.findAll();

        for (Customer customer : customers) {
            double totalPurchaseAmount = customer.getTotalPurchaseAmount();
            String currentTier = customer.getTier();

            // Check and update customer tiers
            if (totalPurchaseAmount < 5000 && !currentTier.equals("Silver")) {
                customer.setTier("Silver");
            } else if (totalPurchaseAmount >= 5000 && totalPurchaseAmount <= 10000 && !currentTier.equals("Golden")) {
                customer.setTier("Golden");
            } else if (totalPurchaseAmount > 10000 && !currentTier.equals("Diamond")) {
                customer.setTier("Diamond");
            }

            // Save updated tiers
            customerRepository.save(customer);
        }
    }
}

