package com.evalincius.placetobeservice.config;

import com.evalincius.placetobeservice.properties.MinioStorageProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Log4j2
@Configuration
@ConditionalOnProperty(
        value="minio.enabled",
        havingValue = "true")
public class MinioStorageServerConfig {
    private MinioStorageProperties minioStorageProperties;
    public MinioStorageServerConfig(final MinioStorageProperties minioStorageProperties){
        this.minioStorageProperties= minioStorageProperties;
    }

    @Bean
    public MinioClient getMinioClient() throws InvalidPortException, InvalidEndpointException, IOException, InvalidKeyException, NoSuchAlgorithmException, InsufficientDataException, InvalidResponseException, InternalException, NoResponseException, InvalidBucketNameException, XmlPullParserException, ErrorResponseException, RegionConflictException {
        MinioClient minioClient = new MinioClient(minioStorageProperties.getEndpoint(), minioStorageProperties.getAccessKey(), minioStorageProperties.getSecretKey());
        boolean isExist = minioClient.bucketExists(minioStorageProperties.getBucketName());
        if(isExist) {
            log.info("Bucket already exists.");
        } else {
            // Make a new bucket called asiatrip to hold a zip file of photos.
            minioClient.makeBucket(minioStorageProperties.getBucketName());
        }
        return minioClient;
    }
}
