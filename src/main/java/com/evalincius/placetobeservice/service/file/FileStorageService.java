package com.evalincius.placetobeservice.service.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file);

    String getFileUrl(String fileID);
}
