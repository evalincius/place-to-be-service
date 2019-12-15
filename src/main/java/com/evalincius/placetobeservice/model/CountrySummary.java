package com.evalincius.placetobeservice.model;

import com.evalincius.placetobeservice.enums.CountryCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountrySummary extends Audit {
    private CountryCode countryCode;
    private String name;
}
