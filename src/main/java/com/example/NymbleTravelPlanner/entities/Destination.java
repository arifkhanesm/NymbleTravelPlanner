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

//    @OneToOne
//    private Activity activities;

}

