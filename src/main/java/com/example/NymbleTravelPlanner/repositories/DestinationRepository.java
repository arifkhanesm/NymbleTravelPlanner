package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.Destination;
import com.example.NymbleTravelPlanner.entities.TravelPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Long> {
}
