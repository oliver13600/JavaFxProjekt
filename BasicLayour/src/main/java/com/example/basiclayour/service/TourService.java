package com.example.basiclayour.service;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.repository.TourRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TourService {
    private final TourRepository tourRepository;

    public TourService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public void save(String tourName) {
        tourRepository.save(new Tour(tourName));
    }

    public List<String> findAll() {
        return tourRepository.findAll()
                .stream()
                .map(Tour::getName)
                .collect(Collectors.toList());
    }
}
