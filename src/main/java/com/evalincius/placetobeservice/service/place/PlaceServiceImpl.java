package com.evalincius.placetobeservice.service.place;

import com.evalincius.placetobeservice.dao.place.PlaceDao;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Place;
import com.evalincius.placetobeservice.service.file.FileStorageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    private PlaceDao placeDao;
    private FileStorageService fileStorageService;
    public PlaceServiceImpl(final PlaceDao placeDao, final FileStorageService fileStorageService){
        this.placeDao = placeDao;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeDao.getAllPlaces();
    }

    @Override
    public String createPlace(Place place) {
        return placeDao.createPlace(place);
    }

    @Override
    public List<Place> getPlacesByCountryAndCity(CountryCode countryCode, String city) {
        return placeDao.getPlacesByCountryAndCity(countryCode, city);
    }

    @Override
    public String storeFile(MultipartFile file) {
        return fileStorageService.storeFile(file);
    }
}
