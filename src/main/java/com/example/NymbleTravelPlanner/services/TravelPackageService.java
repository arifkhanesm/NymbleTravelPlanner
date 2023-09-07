package com.example.NymbleTravelPlanner.services;


    import com.example.NymbleTravelPlanner.entities.TravelPackage;
    import org.springframework.data.domain.Page;
    import org.springframework.data.domain.Pageable;

    import java.util.List;

    public interface TravelPackageService {

        Page<TravelPackage> getAllTravelPackages(Pageable pageable);

        TravelPackage getTravelPackageById(Long id);

        TravelPackage createTravelPackage(TravelPackage travelPackage);

        TravelPackage updateTravelPackage(Long id, TravelPackage updatedTravelPackage);

        boolean deleteTravelPackage(Long id);

    }


