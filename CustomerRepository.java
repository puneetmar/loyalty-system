package loyalty_system.loyaltysystemdemo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import loyalty_system.loyaltysystemdemo.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
        // Custom query to find customers by tier
        List<Customer> findByTier(String tier);
    
        // Custom query to find customers with loyalty points greater than a given value
        List<Customer> findByLoyaltyPointsGreaterThan(int loyaltyPoints);
        
        // Custom query to find customers with a total purchase amount within a specific range
        List<Customer> findByTotalPurchaseAmountBetween(double minAmount, double maxAmount);
        
        // Custom query to find customers by name
        List<Customer> findByName(String name);
}