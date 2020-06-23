package com.evalincius.placetobeservice.service.place;

import com.evalincius.placetobeservice.dao.place.PlaceDao;
import com.evalincius.placetobeservice.enums.CountryCode;
import com.evalincius.placetobeservice.model.Filter;
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
        List<Place> places = placeDao.getAllPlaces();
        enrichWithFileUrl(places);
        return places;
    }

    @Override
    public String createPlace(Place place) {
        return placeDao.createPlace(place);
    }

    @Override
    public List<Place> getPlacesByCountryAndCity(CountryCode countryCode, String city) {
        List<Place> places = placeDao.getPlacesByCountryAndCity(countryCode, city);
        enrichWithFileUrl(places);
        return places;
    }


    @Override
    public List<Place> getPlacesByFilter(Filter filter) {
        List<Place> places = placeDao.getPlacesByFilter(filter);
        enrichWithFileUrl(places);
        return places;
    }

    @Override
    public String storeFile(MultipartFile file) {
        return fileStorageService.storeFile(file);
    }

    private void enrichWithFileUrl(List<Place> places) {
        places.forEach(p -> {
            String url = fileStorageService.getFileUrl(p.getImageId());
            p.setImageURL(url);
        });
    }
}
