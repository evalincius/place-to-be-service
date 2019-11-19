package com.evalincius.placetobeservice.config.arangointerceptor;

import com.arangodb.ArangoDB;

import java.util.function.Supplier;

public class ArangoObjectMethodInterceptor {
    public ArangoObjectMethodInterceptor(Class<ArangoDB> arangoDBClass, Object o, Supplier arangoDB) {
    }
}
