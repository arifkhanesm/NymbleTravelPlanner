package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double cost;
    private int capacity;

//    @OneToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "destination_id")
//    private Destination destination;

}
