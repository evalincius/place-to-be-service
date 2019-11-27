package com.evalincius.placetobeservice.model;

import com.evalincius.placetobeservice.enums.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Country extends Audit {
    private CountryCode countryCode;
    private String name;
    private List<Coordinates> coordinates;
}
