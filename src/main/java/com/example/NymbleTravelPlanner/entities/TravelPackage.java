package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Data
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String travelPackageName;
    private int passengerCapacity;
    private int numberOfPassengerEnrolled;
    private int seatAvailable;
    @OneToMany(mappedBy = "travelPackage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Destination> destinations;
}
