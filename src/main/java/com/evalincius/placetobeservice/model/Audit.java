package com.evalincius.placetobeservice.model;

import com.arangodb.entity.BaseDocument;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Audit extends BaseDocument {
    private LocalDateTime createdDate;
}
