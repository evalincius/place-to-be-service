package com.evalincius.placetobeservice.model;

import com.arangodb.entity.BaseDocument;
import lombok.Data;

import java.util.Date;
@Data
public class Audit extends BaseDocument {
    private Date createdDate;
}
