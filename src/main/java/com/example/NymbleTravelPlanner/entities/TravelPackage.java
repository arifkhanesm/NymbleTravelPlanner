package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
public class TravelPackage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "travelPackage_id")
    private Long id;
    private String travelPackageName;
    private int passengerCapacity;
    private int numberOfPassengerEnrolled;
    private int seatAvailable;



    @OneToMany(targetEntity = Destination.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "travelPackage_id",referencedColumnName = "travelPackage_id")
    private List<Destination> destinations=new ArrayList<>();
}
