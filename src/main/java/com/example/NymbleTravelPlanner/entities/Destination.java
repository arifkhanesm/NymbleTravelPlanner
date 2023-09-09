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

    @OneToOne(targetEntity = Activity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "activity_id")
    private Activity activities;

}

