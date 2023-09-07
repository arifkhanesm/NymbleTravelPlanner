package com.example.NymbleTravelPlanner.globalExceptionHandler;

public class TravelPackageExist extends RuntimeException{
    public TravelPackageExist(String message) {
        super(message);
    }
}
