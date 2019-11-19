package com.evalincius.placetobeservice.service;

import com.evalincius.placetobeservice.dao.PlaceDao;
import com.evalincius.placetobeservice.model.Place;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlaceServiceImpl implements PlaceService {
    private PlaceDao placeDao;
    public PlaceServiceImpl(PlaceDao placeDao){
        this.placeDao = placeDao;
    }

    @Override
    public List<Place> getAllPlaces() {
        return placeDao.getAllPlaces();
    }
}
