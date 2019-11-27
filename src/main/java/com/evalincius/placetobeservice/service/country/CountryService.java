package com.evalincius.placetobeservice.service.country;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;

public interface CountryService {
    public Country getCountry(CountryCode countryCode);

    String createCountry(Country country);
}
