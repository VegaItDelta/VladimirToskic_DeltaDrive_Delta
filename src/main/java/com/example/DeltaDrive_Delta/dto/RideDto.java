package com.example.DeltaDrive_Delta.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDto {
    private Long vehicleId;
    private double longitudeStart;
    private double latitudeStart;
    private double longitudeEnd;
    private double latitudeEnd;
}
