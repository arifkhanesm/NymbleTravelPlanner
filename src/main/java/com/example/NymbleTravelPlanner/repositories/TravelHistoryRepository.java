package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.TravelHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TravelHistoryRepository extends JpaRepository<TravelHistory,Long> {
}
