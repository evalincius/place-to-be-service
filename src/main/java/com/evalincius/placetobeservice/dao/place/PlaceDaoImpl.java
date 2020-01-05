package com.evalincius.placetobeservice.dao.place;

import com.arangodb.ArangoCursor;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.evalincius.placetobeservice.ArangoPersistentManager;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Place;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PlaceDaoImpl implements PlaceDao{
    private static String COLLECTION = "place";
    private static String GET_PLACES_BY_COUNTRY_N_CITY = "FOR p IN @@collection FILTER p.countryCode == @countryCode AND p.city == @city RETURN p";
    private ArangoPersistentManager persistentManager;
    public PlaceDaoImpl(ArangoPersistentManager persistentManager){
        this.persistentManager = persistentManager;
    }

    @Override
    public List<Place> getAllPlaces() {
        Map map = new MapBuilder().put("@collection", COLLECTION).get();
        ArangoCursor cursor = persistentManager.getDatabase().query(
                "FOR i IN @@collection RETURN i",
                map,
                new AqlQueryOptions(),
                Place.class);
        return cursor.asListRemaining();
    }

    @Override
    public String createPlace(Place place) {
        return persistentManager.insert(COLLECTION, place);
    }

    @Override
    public List<Place> getPlacesByCountryAndCity(CountryCode countryCode, String city) {
        Map map = new MapBuilder()
                .put("@collection", COLLECTION)
                .put("countryCode", countryCode)
                .put("city", city)
                .get();
        ArangoCursor cursor = persistentManager.getDatabase().query(
                GET_PLACES_BY_COUNTRY_N_CITY,
                map,
                new AqlQueryOptions(),
                Place.class);
        return cursor.asListRemaining();
    }
}
