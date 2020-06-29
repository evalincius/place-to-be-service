package com.evalincius.placetobeservice.model;

import com.evalincius.placetobeservice.enums.CountryCode;
import lombok.Data;

import java.util.List;

@Data
public class Filter {
    List<CountryCode> country;
    String search;
    List<String> city;
}
