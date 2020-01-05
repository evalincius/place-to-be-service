package com.evalincius.placetobeservice;

import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.util.MapBuilder;
import com.evalincius.placetobeservice.model.Audit;

import java.time.LocalDateTime;
import java.util.Map;

public class ArangoPersistentManager {

    private ArangoDatabase arangoDatabase;

    public ArangoPersistentManager(ArangoDB arangoDB, String schema) {
        arangoDatabase = arangoDB.db(schema);
    }

    public ArangoDatabase getDatabase() {
        return arangoDatabase;
    }

    public <T extends Audit> String createOrUpdate(String collectionName, T doc) {
        Map map = new MapBuilder()
                .put("@collection", collectionName)
                .put("key", doc.getKey())
                .put("doc", doc)
                .get();
        ArangoCursor cursor = arangoDatabase.query(
                "UPSERT {_key : @key} INSERT @doc REPLACE @doc IN @@collection RETURN NEW._key",
                map,
                new AqlQueryOptions(),
                String.class);
        return cursor.hasNext() ? (String) cursor.next() : null;
    }

    public <T extends Audit> String insert(String collectionName, T doc) {
        doc.setCreatedDate(LocalDateTime.now());
        return arangoDatabase.collection(collectionName).insertDocument(doc).getKey();
    }
}

