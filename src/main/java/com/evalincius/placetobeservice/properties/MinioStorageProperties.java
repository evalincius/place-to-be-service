package com.evalincius.placetobeservice.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("minio")
@Data
public class MinioStorageProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
