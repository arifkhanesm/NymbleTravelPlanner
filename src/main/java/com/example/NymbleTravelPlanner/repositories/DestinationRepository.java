package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.Destination;
import com.example.NymbleTravelPlanner.entities.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
