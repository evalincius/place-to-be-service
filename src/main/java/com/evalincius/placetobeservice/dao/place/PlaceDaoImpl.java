package com.evalincius.placetobeservice.dao.place;

import com.arangodb.ArangoCursor;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.evalincius.placetobeservice.ArangoPersistentManager;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Filter;
import com.evalincius.placetobeservice.model.Place;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;

@Repository
public class PlaceDaoImpl implements PlaceDao{
    private static String COLLECTION = "place";
    private static String GET_PLACES_BY_COUNTRY_N_CITY = "FOR d IN "+ COLLECTION +
            " FILTER d.countryCode == @countryCode" +
            " AND d.city == @city" +
            " RETURN d";
    private static String GET_PLACES_QUERY_HEADER = "FOR d IN "+ COLLECTION;
    private static String GET_PLACES_QUERY_FOOTER = " RETURN d";
    private static String GET_PLACES_CITY_FILTER_QUERY = " FILTER d.countryCode == @countryCode";
    private static String GET_PLACES_COUNTRY_FILTER_QUERY = " FILTER d.city == @city";
    private static String GET_PLACES_FILTER_QUERY =
            " FILTER LIKE(d.city, CONCAT('%', @filter, '%'), true)" +
            " OR LIKE(d.address, CONCAT('%', @filter, '%'), true)" +
            " OR LIKE(d.countryCode, CONCAT('%', @filter, '%'), true)" +
            " OR LIKE(d.name, CONCAT('%', @filter, '%'), true)";

    private ArangoPersistentManager persistentManager;
    public PlaceDaoImpl(ArangoPersistentManager persistentManager){
        this.persistentManager = persistentManager;
    }

    @Override
    public List<Place> getAllPlaces() {
        ArangoCursor cursor = persistentManager.getDatabase().query(
                "FOR i IN "+ COLLECTION +" RETURN i",
                null,
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

    @Override
    public List<Place> getPlacesByFilter(Filter filter) {
        ArangoCursor cursor = persistentManager.getDatabase().query(
                createQuery(filter),
                createBindVars(filter),
                new AqlQueryOptions(),
                Place.class);
        return cursor.asListRemaining();
    }

    private Map<String, Object> createBindVars(Filter filter) {
        MapBuilder mapBuilder = new MapBuilder();
        if(!StringUtils.isEmpty(filter.getSearch())) {
            mapBuilder.put("filter", filter.getSearch());
        }
        if(!StringUtils.isEmpty(filter.getCountry())) {
            mapBuilder.put("countryCode", filter.getCountry());
        }
        if(!StringUtils.isEmpty(filter.getCity())) {
            mapBuilder.put("city", filter.getCity());
        }
        return mapBuilder.get();
    }

    private String createQuery(Filter filter) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(GET_PLACES_QUERY_HEADER);
        if(!StringUtils.isEmpty(filter.getSearch())) {
            stringBuilder.append(GET_PLACES_FILTER_QUERY);
        }
        if(!StringUtils.isEmpty(filter.getCountry())) {
            stringBuilder.append(GET_PLACES_COUNTRY_FILTER_QUERY);
        }
        if(!StringUtils.isEmpty(filter.getCity())) {
            stringBuilder.append(GET_PLACES_CITY_FILTER_QUERY);
        }
        stringBuilder.append(GET_PLACES_QUERY_FOOTER);

        return stringBuilder.toString();
    }
}
