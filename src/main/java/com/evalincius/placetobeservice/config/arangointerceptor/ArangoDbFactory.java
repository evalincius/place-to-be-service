package com.evalincius.placetobeservice.config.arangointerceptor;

import com.arangodb.ArangoDB;
import org.springframework.cglib.proxy.Proxy;

import java.util.function.Supplier;

public class ArangoDbFactory implements Supplier<ArangoDB> {
    private ArangoDbBuilderFactory arangoDbBuilderFactory;
    public ArangoDbFactory(ArangoDbBuilderFactory arangoDbBuilderFactory){
        this.arangoDbBuilderFactory = arangoDbBuilderFactory;
    }
    @Override
    public ArangoDB get() {
        return this.createArangoDbProxy();
    }

    private ArangoDB createArangoDbProxy() {
        return (ArangoDB) Proxy.newProxyInstance(ArangoDB.class.getClassLoader(), new Class[]{ArangoDB.class}, new ArangoInterceptor(ArangoDB.class, (Object)null, new ArangoDbSupplier()));
    }

    private class ArangoDbSupplier  implements Supplier<ArangoDB> {
        @Override
        public ArangoDB get() {
            return ArangoDbFactory.this.arangoDbBuilderFactory.get().build();
        }
    }
}
