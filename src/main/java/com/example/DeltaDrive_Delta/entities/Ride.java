package com.example.DeltaDrive_Delta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="ride")
@Builder
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
    private Long vehicleId;
    private double price;
    private double longitudeStart;
    private double latitudeStart;
    private double longitudeEnd;
    private double latitudeEnd;
    private double distance;
    private double duration;
    private RideStatus status;
    @ManyToOne
    @JoinColumn(name = "rate_id")
    private Rate rate;
}
