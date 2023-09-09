package com.example.NymbleTravelPlanner.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingDto {
    private Long passengerId;
    private Long travelPackageId;
    private List<Integer> destinationIds;
    private List<Integer> activityIds;

}
