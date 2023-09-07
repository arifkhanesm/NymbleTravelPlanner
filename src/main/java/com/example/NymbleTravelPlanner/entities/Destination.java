package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "travelPackage_id")
    private TravelPackage travelPackage;

//    @OneToOne
//    private Activity activities;

}

