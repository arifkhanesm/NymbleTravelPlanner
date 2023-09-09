package com.example.NymbleTravelPlanner.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private double cost;
    private int capacity;
}
