package com.example.DeltaDrive_Delta.repository;

import com.example.DeltaDrive_Delta.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {


    @Query(value = "SELECT v FROM Vehicle v ORDER BY " +
            "SQRT(POWER(v.longitude - :longitude, 2) + POWER(v.latitude - :latitude, 2))")
    List<Vehicle> findNearestVehicles(@Param("longitude") double longitude,
                                      @Param("latitude") double latitude);
   }
