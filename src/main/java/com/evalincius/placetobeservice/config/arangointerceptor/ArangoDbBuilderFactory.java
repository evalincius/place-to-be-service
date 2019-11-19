package com.evalincius.placetobeservice.config.arangointerceptor;

import com.arangodb.ArangoDB.Builder;

import java.util.function.Supplier;

public interface ArangoDbBuilderFactory extends Supplier<Builder> {
}
