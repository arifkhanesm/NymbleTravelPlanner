package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "destination_id")
    private Long id;

    private String name;


    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "travel_package_id")
    private TravelPackage travelPackage;

//    @OneToOne
//    private Activity activities;

}

