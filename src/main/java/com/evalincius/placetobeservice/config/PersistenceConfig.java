package com.evalincius.placetobeservice.config;

import com.arangodb.ArangoDB;
import com.arangodb.ArangoDB.Builder;
import com.arangodb.velocypack.module.jdk8.VPackJdk8Module;
import com.evalincius.placetobeservice.ArangoPersistentManager;
import com.evalincius.placetobeservice.config.arangointerceptor.ArangoBuilderEnhancer;
import com.evalincius.placetobeservice.config.arangointerceptor.ArangoDbBuilderFactory;
import com.evalincius.placetobeservice.config.arangointerceptor.ArangoDbFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersistenceConfig {

    @Value("${datasource.arangodb.schema}")
    private String schema;
    @Value("${datasource.arangodb.host}")
    private String host;
    @Value("${datasource.arangodb.port}")
    private int port;
    @Value("${datasource.arangodb.user}")
    private String user;
    @Value("${datasource.arangodb.password}")
    private String password;
    @Value("${datasource.arangodb.useSsl}")
    private boolean useSsl;

    @Bean
    public ArangoBuilderEnhancer localArangoBuilderEnhancer(){
        return (builder) -> builder;
    }

    @Bean
    public ArangoDbBuilderFactory localArangoDbBuilderFactory(ArangoBuilderEnhancer arangoBuilderEnhancer){
        return () -> arangoBuilderEnhancer.enhance(new Builder())
                 .registerModule(new VPackJdk8Module())
                 .host(host, port)
                 .user(user)
                 .password(password)
                 .useSsl(useSsl);
    }

    @Bean
    public ArangoDbFactory localArangoDbFactory(ArangoDbBuilderFactory arangoDbBuilderFactory){
        return new ArangoDbFactory(arangoDbBuilderFactory);
    }

    @Bean
    public ArangoDB arangoDatabaseConfig(ArangoDbFactory arangoDbFactory){
        return arangoDbFactory.get();
    }

    @Bean
    public ArangoPersistentManager getArangoPersistentManager(ArangoDB arangoDB, @Value("${datasource.arangodb.schema}") String schema) {
        return new ArangoPersistentManager(arangoDB, schema);
    }
}
