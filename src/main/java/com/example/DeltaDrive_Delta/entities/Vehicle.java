package com.example.DeltaDrive_Delta.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name="vehicle")
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private String driverFirstName;
    private String driverLastName;
    private Double latitude;
    private Double longitude;
    private String startPrice;
    private String pricePerKm;
}
