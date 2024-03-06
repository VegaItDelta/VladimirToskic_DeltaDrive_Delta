package com.example.DeltaDrive_Delta.controller;

import com.example.DeltaDrive_Delta.dto.RideDto;
import com.example.DeltaDrive_Delta.entities.Ride;
import com.example.DeltaDrive_Delta.services.impl.RideServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/ride")
@RequiredArgsConstructor
public class RideController {

    @Autowired
    private RideServiceImpl rideService;

    @PostMapping(value = "/book-ride")
    public ResponseEntity<Ride> bookRide(@RequestBody RideDto rideDto){
        return  ResponseEntity.ok(rideService.bookRide(rideDto));
    }



    @GetMapping(value = "/{id}/randomize-status")
    public Ride randomizeRideStatus(@PathVariable Long id){
        return rideService.randomizeRideStatus(id);
    }

    @GetMapping(value = "/{id}/simulate-ride")
    public Ride simulteRide(@PathVariable Long id){
        return rideService.simulateRide(id);
    }
}
