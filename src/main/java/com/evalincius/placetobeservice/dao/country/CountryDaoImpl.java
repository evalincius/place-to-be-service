package com.evalincius.placetobeservice.dao.country;

import com.arangodb.ArangoCursor;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.evalincius.placetobeservice.ArangoPersistentManager;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Country;
import com.evalincius.placetobeservice.model.CountrySummary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CountryDaoImpl implements CountryDao {
    private static String COLLECTION = "country";
    private static String GET_COUNTRY_SUMMARY_LIST_QUERY = "FOR i IN @@collection RETURN i";
    private ArangoPersistentManager persistentManager;

    public CountryDaoImpl(ArangoPersistentManager persistentManager) {
        this.persistentManager = persistentManager;
    }

    @Override
    public Country get(CountryCode countryCode) {
        Map map = new MapBuilder().put("@collection", COLLECTION).get();
        ArangoCursor cursor = persistentManager.getDatabase().query(
                "FOR i IN @@collection RETURN i",
                map,
                new AqlQueryOptions(),
                Country.class);
        return cursor.hasNext() ? (Country)cursor.next() : null;
    }

    @Override
    public String create(Country country) {
        return persistentManager.insert(COLLECTION, country);
    }

    @Override
    public List<CountrySummary> getCountrySummaries() {
        Map map = new MapBuilder().put("@collection", COLLECTION).get();
        ArangoCursor cursor = persistentManager.getDatabase().query(
                GET_COUNTRY_SUMMARY_LIST_QUERY,
                map,
                new AqlQueryOptions(),
                CountrySummary.class);
        return cursor.asListRemaining();
    }
}
