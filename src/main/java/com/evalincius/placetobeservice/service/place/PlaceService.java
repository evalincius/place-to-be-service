package com.evalincius.placetobeservice.service.place;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Place;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PlaceService {
    List<Place> getAllPlaces();

    String createPlace(Place place);

    List<Place> getPlacesByCountryAndCity(CountryCode countryCode, String city);

    String storeFile(MultipartFile file);
}
