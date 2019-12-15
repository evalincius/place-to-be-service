package com.evalincius.placetobeservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place extends Audit {
    private String name;
    private String countryCode;
    private String city;
    private Coordinates coordinates;
    private String imageId;
    private String address;
}
