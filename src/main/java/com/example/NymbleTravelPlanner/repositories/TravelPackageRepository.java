package com.example.NymbleTravelPlanner.repositories;

import com.example.NymbleTravelPlanner.entities.TravelPackage;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;


@Repository
public interface TravelPackageRepository extends JpaRepository<TravelPackage, Long> {
    Optional<TravelPackage> findBytravelPackageName(String name);
}
