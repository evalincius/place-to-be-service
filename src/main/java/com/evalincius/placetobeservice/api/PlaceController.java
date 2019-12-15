package com.evalincius.placetobeservice.api;

import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Place;
import com.evalincius.placetobeservice.model.file.FileMetadata;
import com.evalincius.placetobeservice.service.place.PlaceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/place")
public class PlaceController {

    private PlaceService placeService;

    public PlaceController(PlaceService placeService) {
        this.placeService = placeService;
    }

    @GetMapping("list")
    public List<Place> getAllPlaces() {
        return placeService.getAllPlaces();
    }

    @GetMapping("list/{countryCode}/{city}")
    public List<Place> getPlacesByCountryAndCity(@PathVariable CountryCode countryCode, @PathVariable String city) {
        return placeService.getPlacesByCountryAndCity(countryCode, city);
    }

    @PostMapping
    public String createPlace(@RequestBody Place place) {
        return placeService.createPlace(place);
    }

    @PostMapping("/uploadFile")
    public FileMetadata uploadFile(@RequestParam("file") MultipartFile file) {
        String id = placeService.storeFile(file);
        return FileMetadata.builder()
                .id(id)
                .fileName(file.getOriginalFilename())
                .size(file.getSize())
                .fileType(file.getContentType())
                .build();
    }
}
