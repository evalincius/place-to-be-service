package com.evalincius.placetobeservice.service.file;

import com.evalincius.placetobeservice.properties.MinioStorageProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@ConditionalOnProperty(
        value="minio.enabled",
        havingValue = "true")
public class MinioFileStorageService implements FileStorageService {
    private MinioClient minioClient;
    private MinioStorageProperties minioStorageProperties;
    public MinioFileStorageService(final MinioClient minioClient, final MinioStorageProperties minioStorageProperties) {
        this.minioClient = minioClient;
        this.minioStorageProperties = minioStorageProperties;
    }
    @Override
    public String storeFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString();
        try {
            minioClient.putObject(minioStorageProperties.getBucketName(), fileName, file.getInputStream(), file.getContentType());
        } catch (IOException | InvalidBucketNameException | NoSuchAlgorithmException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException | InvalidArgumentException | InsufficientDataException | InvalidResponseException e) {
            e.printStackTrace();
        }
        return fileName;
    }
}
