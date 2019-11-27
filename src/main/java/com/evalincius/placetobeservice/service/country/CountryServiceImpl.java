package com.evalincius.placetobeservice.service.country;

import com.evalincius.placetobeservice.dao.country.CountryDao;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import org.springframework.stereotype.Service;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryDao countryDao;

    public CountryServiceImpl(final CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    public Country getCountry(CountryCode countryCode) {
        return countryDao.get(countryCode);
    }

    @Override
    public String createCountry(Country country) {
        return countryDao.create(country);
    }
}
