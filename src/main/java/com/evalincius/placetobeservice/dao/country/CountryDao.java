package com.evalincius.placetobeservice.dao.country;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;

public interface CountryDao {
    Country get(final CountryCode countryCode);

    String create(Country country);
}
