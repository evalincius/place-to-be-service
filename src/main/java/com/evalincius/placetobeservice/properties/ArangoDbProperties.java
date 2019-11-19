package com.evalincius.placetobeservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("datasource.arangodb")
@Data
public class ArangoDbProperties {
    private String host;
    private String user;
    private String password;
    private String useSsl;
}
