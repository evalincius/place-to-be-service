package com.evalincius.placetobeservice.service.country;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import com.evalincius.placetobeservice.model.CountrySummary;

import java.util.List;

public interface CountryService {
    Country getCountry(CountryCode countryCode);

    String createCountry(Country country);

    List<CountrySummary> getCountrySummaries();
}
