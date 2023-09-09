package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
