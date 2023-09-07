package com.example.NymbleTravelPlanner.globalExceptionHandler;

public class TravelPackageNotFoundException extends RuntimeException {

    public TravelPackageNotFoundException(String message) {
        super(message);
    }
}