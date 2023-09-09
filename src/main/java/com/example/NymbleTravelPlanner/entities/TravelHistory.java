package com.example.NymbleTravelPlanner.entities;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Data
public class TravelHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "travel_history_id")
    private Long id;
    //price paid after discount
    private double pricePaid; //done
    private double actualPrice; //done
    private String activityName; //done
    private String destinationName; //done
    private Long destinationId; //done
    private String packageName;
}
