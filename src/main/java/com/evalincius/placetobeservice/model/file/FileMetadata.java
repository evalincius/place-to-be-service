package com.evalincius.placetobeservice.model.file;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FileMetadata {
    String id;
    String fileName;
    String fileType;
    Long size;
}