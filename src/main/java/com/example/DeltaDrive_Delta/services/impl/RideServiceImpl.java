package com.example.DeltaDrive_Delta.services.impl;

import com.example.DeltaDrive_Delta.Exceptions.RideNotFoundException;
import com.example.DeltaDrive_Delta.dto.RideDto;
import com.example.DeltaDrive_Delta.entities.Ride;
import com.example.DeltaDrive_Delta.entities.RideStatus;
import com.example.DeltaDrive_Delta.entities.Vehicle;
import com.example.DeltaDrive_Delta.repository.RideRepository;
import com.example.DeltaDrive_Delta.services.RideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class RideServiceImpl implements RideService {

    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double VEHICLE_SPEED_KM = 60.0;
    @Autowired
    RideRepository rideRepository;

    @Autowired
    VehicleServiceImpl vehicleService;

    @Autowired
    private UserServiceImpl userService;
    @Override
    public Ride bookRide(RideDto rideDto) {
        double distance = calculateDistance(rideDto);
        Vehicle vehicle = vehicleService.getVehicleById(rideDto.getVehicleId());
        double startPrice = Double.parseDouble(vehicle.getStartPrice().replaceAll("[^\\d.]", ""));
        double pricePerKm = Double.parseDouble(vehicle.getPricePerKm().replaceAll("[^\\d.]", ""));
        double totalPrice = startPrice + (pricePerKm * distance);
        double speedInKilometersPerSecond = VEHICLE_SPEED_KM * 1000.0 / 3600.0;//60km/h kovenrtujemo
        double durationInSeconds = distance / speedInKilometersPerSecond;

        Ride ride = Ride.builder()
                .user(userService.getLoggedUser())
                .vehicleId(rideDto.getVehicleId())
                .price(totalPrice)
                .longitudeStart(rideDto.getLongitudeStart())
                .latitudeStart(rideDto.getLatitudeStart())
                .longitudeEnd(rideDto.getLongitudeEnd())
                .latitudeEnd(rideDto.getLatitudeEnd())
                .distance(distance)
                .duration(durationInSeconds/60)
                .status(RideStatus.PENDING)
                .build();

        return rideRepository.save(ride);
    }

    @Override
    public Ride randomizeRideStatus(Long rideId) {
        return rideRepository.findById(rideId)
                .map(ride -> {
                    ride.setStatus(getRandomRideStatus());
                    return rideRepository.save(ride);
                })
                .orElseThrow(() -> new RideNotFoundException("Ride not found with ID: " + rideId));
    }
    private double calculateDistance(RideDto rideDto){
        return calculateDist(rideDto.getLatitudeStart(), rideDto.getLongitudeStart(), rideDto.getLatitudeEnd(), rideDto.getLongitudeEnd());
    }
    @Override
    public Ride simulateRide(Long rideId){
        return rideRepository.findById(rideId)
                .map(ride -> {
                    ride.setStatus(RideStatus.COMPLETED);
                    return rideRepository.save(ride);
                })
                .orElseThrow(() -> new RideNotFoundException("Ride not found with ID: " + rideId));
    }

    public RideStatus getRandomRideStatus() {
        int randomNumber = new Random().nextInt(100);
        if (randomNumber < 75) {
            return RideStatus.ACCEPTED;
        } else {
            return RideStatus.REJECTED;
        }
    }

//Haversine formula to calculate the distance between two points specified by latitude and longitude
    public static double calculateDist(double lat1, double lon1, double lat2, double lon2) {
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS_KM * c;
    }


}
