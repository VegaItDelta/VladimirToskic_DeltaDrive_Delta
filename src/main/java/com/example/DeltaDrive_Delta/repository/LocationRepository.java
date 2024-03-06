package com.example.DeltaDrive_Delta.repository;

import com.example.DeltaDrive_Delta.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location,Double> {
}
