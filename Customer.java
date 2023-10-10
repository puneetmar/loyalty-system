package loyalty_system.loyaltysystemdemo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double totalPurchaseAmount;
    private String tier; // Silver, Golden, Diamond
    public void setLoyaltyPoints(int loyaltyPoints) {
    }

    
}
