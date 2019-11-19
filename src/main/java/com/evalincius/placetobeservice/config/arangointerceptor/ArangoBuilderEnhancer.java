package com.evalincius.placetobeservice.config.arangointerceptor;

import com.arangodb.ArangoDB.Builder;

public interface ArangoBuilderEnhancer {
    Builder enhance(Builder arangoDbBuilder);
}
