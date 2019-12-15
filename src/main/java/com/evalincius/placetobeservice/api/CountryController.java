package com.evalincius.placetobeservice.api;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import com.evalincius.placetobeservice.model.CountrySummary;
import com.evalincius.placetobeservice.service.country.CountryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/country")
public class CountryController {
    private CountryService countryService;
    public CountryController(final CountryService countryService){
        this.countryService = countryService;
    }

    @GetMapping("/list")
    public List<Country> getAllCountries(){
        return null;
    }

    @GetMapping("/summary/list")
    public List<CountrySummary> getCountrySummaries(){
        return countryService.getCountrySummaries();
    }

    @GetMapping("/{countryCode}")
    public Country getAllCountries(@PathVariable CountryCode countryCode){
        return countryService.getCountry(countryCode);
    }

    @PostMapping
    public String createCountry(@RequestBody Country country){
        return countryService.createCountry(country);
    }

}
