package com.evalincius.placetobeservice.model;

import com.evalincius.placetobeservice.enums.CountryCode;
import lombok.Data;

@Data
public class Filter {
    CountryCode country;
    String search;
    String city;
}
