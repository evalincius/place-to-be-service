package com.evalincius.placetobeservice.api;

import com.evalincius.placetobeservice.service.city.CityService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/city")
public class CityController {
    private final CityService cityService;

    public CityController(final CityService cityService){
        this.cityService = cityService;
    }
}
