package com.evalincius.placetobeservice.dao.place;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Place;

import java.util.List;

public interface PlaceDao {
    List<Place> getAllPlaces();

    String createPlace(Place place);

    List<Place> getPlacesByCountryAndCity(CountryCode countryCode, String city);
}
