package com.evalincius.placetobeservice.dao.country;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import com.evalincius.placetobeservice.model.CountrySummary;

import java.util.List;

public interface CountryDao {
    Country get(final CountryCode countryCode);

    String create(Country country);

    List<CountrySummary> getCountrySummaries();
}
