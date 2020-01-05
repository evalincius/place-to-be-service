package com.evalincius.placetobeservice.model;

import com.arangodb.velocypack.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Place extends Audit {
    private String name;
    private String countryCode;
    private String city;
    private Coordinates coordinates;
    private String imageId;
    @Expose(serialize = false, deserialize = false)
    private String imageURL;
    private String address;
}
