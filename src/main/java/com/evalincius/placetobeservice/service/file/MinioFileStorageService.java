package com.evalincius.placetobeservice.service.file;

import com.evalincius.placetobeservice.properties.MinioStorageProperties;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@Service
@Log4j2
@ConditionalOnProperty(
        value="minio.enabled",
        havingValue = "true")
public class MinioFileStorageService implements FileStorageService {
    // default expiration for a presigned URL is 4 hours in seconds
    private static final int DEFAULT_EXPIRY_TIME = 1 * 4 * 3600; // days * hours * seconds
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
            minioClient.putObject(minioStorageProperties.getBucketName(), fileName, file.getInputStream(), file.getSize(), null, null, file.getContentType());
            log.info("{} was uploaded successful", file.getOriginalFilename());
        } catch (IOException | InvalidBucketNameException | NoSuchAlgorithmException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException | InvalidArgumentException | InsufficientDataException | InvalidResponseException e) {
            log.error("Failed to store file", e);
        }
        return fileName;
    }

    @Override
    public String getFileUrl(String fileID) {
        String fileUrl = null;
        try {
            fileUrl = minioClient.getPresignedObjectUrl(Method.GET, minioStorageProperties.getBucketName(), fileID, DEFAULT_EXPIRY_TIME, null);
            log.info("{} was generated successful", fileUrl);
        } catch (IOException | InvalidBucketNameException | NoSuchAlgorithmException | InvalidKeyException | NoResponseException | XmlPullParserException | ErrorResponseException | InternalException | InsufficientDataException | InvalidResponseException | InvalidExpiresRangeException e) {
            log.error("Failed to store file", e);
        }
        return fileUrl;
    }
}
