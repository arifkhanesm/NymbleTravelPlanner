package com.example.NymbleTravelPlanner.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String passengerNumber;

    // Enum for passenger types: Standard, Gold, Premium
    public enum PassengerType {
        STANDARD, GOLD, PREMIUM
    }

    @Enumerated(EnumType.STRING)
    private PassengerType passengerType;

    // Balance for standard and gold passengers
    private double balance;

}

