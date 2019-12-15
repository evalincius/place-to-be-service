package com.evalincius.placetobeservice.service.country;

import com.evalincius.placetobeservice.dao.country.CountryDao;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import com.evalincius.placetobeservice.model.CountrySummary;
import com.evalincius.placetobeservice.service.file.FileStorageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService{
    private CountryDao countryDao;
    private FileStorageService fileStorageService;

    public CountryServiceImpl(final CountryDao countryDao, final FileStorageService fileStorageService) {
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

    @Override
    public List<CountrySummary> getCountrySummaries() {
        return countryDao.getCountrySummaries();
    }
}
