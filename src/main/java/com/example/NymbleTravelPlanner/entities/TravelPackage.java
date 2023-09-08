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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String travelPackageName;
    private int passengerCapacity;
    private int numberOfPassengerEnrolled;
    private int seatAvailable;
    @OneToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "package_destinations", joinColumns = { @JoinColumn(name = "package_id") }, inverseJoinColumns = { @JoinColumn(name = "destination_id") })
    private List<Destination> destinations;
}
