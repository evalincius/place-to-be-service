package com.evalincius.placetobeservice.api;

import com.evalincius.placetobeservice.models.Place;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/places")
public class PlaceController {

    @GetMapping
    public List<Place> getAllPlaces() {
        return Collections.singletonList(Place.builder().name("Something").build());
    }
}
