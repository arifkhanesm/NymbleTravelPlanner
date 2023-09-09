package com.example.NymbleTravelPlanner.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter
@Setter
@Data
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passenger_id")
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

    @OneToMany(targetEntity = TravelHistory.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "passenger_id",referencedColumnName = "passenger_id")
    private List<TravelHistory> travelHistory=new ArrayList<>();

}

