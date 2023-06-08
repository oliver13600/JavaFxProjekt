package com.example.basiclayour.service;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.repository.TourRepository;

import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    private final TourRepository tourRepository;

    private String keyword;


    public SearchService(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public List<String> findToursByKeyword(){
        return tourRepository.findToursByKeyword(keyword)
                .stream()
                .map(Tour::getName)
                .collect(Collectors.toList());
    }

    public void searchTours(){tourRepository.searchTours();}


}
