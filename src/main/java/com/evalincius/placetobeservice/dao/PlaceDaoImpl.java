package com.evalincius.placetobeservice.dao;

import com.arangodb.ArangoCursor;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.evalincius.placetobeservice.ArangoPersistentManager;
import com.evalincius.placetobeservice.model.Place;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class PlaceDaoImpl implements PlaceDao{
    private static String COLLECTION = "place";
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
}
