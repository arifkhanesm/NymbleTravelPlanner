package com.example.NymbleTravelPlanner.controller;

import com.example.NymbleTravelPlanner.entities.TravelPackage;
import com.example.NymbleTravelPlanner.services.TravelPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/travel-packages")
public class TravelPackageController {

    private final TravelPackageService travelPackageService;

    @Autowired
    public TravelPackageController(TravelPackageService travelPackageService) {
        this.travelPackageService = travelPackageService;
    }

    // Endpoint to retrieve all travel packages
    @GetMapping
    public ResponseEntity<Page<TravelPackage>> getAllTravelPackages(Pageable pageable) {
        Page<TravelPackage> travelPackages = travelPackageService.getAllTravelPackages(pageable);
        return ResponseEntity.ok(travelPackages);
    }

    // Endpoint to retrieve a specific travel package by ID
    @GetMapping("/{id}")
    public ResponseEntity<TravelPackage> getTravelPackageById(@PathVariable Long id) {
        TravelPackage travelPackage = travelPackageService.getTravelPackageById(id);
        if (travelPackage != null) {
            return ResponseEntity.ok(travelPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to create a new travel package
    @PostMapping
    public ResponseEntity<TravelPackage> createTravelPackage(@RequestBody TravelPackage travelPackage) {
        TravelPackage createdTravelPackage = travelPackageService.createTravelPackage(travelPackage);
        return new ResponseEntity<>(createdTravelPackage, HttpStatus.CREATED);
    }

    // Endpoint to update an existing travel package by ID
    @PutMapping("/{id}")
    public ResponseEntity<TravelPackage> updateTravelPackage(@PathVariable Long id, @RequestBody TravelPackage updatedTravelPackage) {
        TravelPackage travelPackage = travelPackageService.updateTravelPackage(id, updatedTravelPackage);
        if (travelPackage != null) {
            return ResponseEntity.ok(travelPackage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint to delete a travel package by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTravelPackage(@PathVariable Long id) {
        boolean deleted = travelPackageService.deleteTravelPackage(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
