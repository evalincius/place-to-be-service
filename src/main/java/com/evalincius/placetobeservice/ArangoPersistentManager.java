package com.evalincius.placetobeservice;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDatabase;

public class ArangoPersistentManager {

    private ArangoDatabase arangoDatabase;
    public ArangoPersistentManager(ArangoDB arangoDB, String schema) {
        arangoDatabase = arangoDB.db(schema);
    }

    public ArangoDatabase getDatabase() {
        return arangoDatabase;
    }
}

