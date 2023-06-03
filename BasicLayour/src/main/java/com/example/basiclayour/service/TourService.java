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

    public void save(String tourInfo) {

        String tourInformationSplit[] = tourInfo.split(" ");
        // From Wien to Salzburg in 02:48:26 (296.0)

        String tourName = tourInformationSplit[1] + "-" + tourInformationSplit[2] + "-" + tourInformationSplit[3];

        String fromStart = tourInformationSplit[1];
        String toFinish = tourInformationSplit[3];

        String tourDescription = "No Description yet";
        String transportType = "Work in Progress";


        String tourDistanceString = tourInformationSplit[6].replaceAll("[\\p{Ps}\\p{Pe}]"," ");
        float tourDistance = Float.parseFloat(tourDistanceString);

        String estimatedTimeString[] = tourInformationSplit[5].split(":");
        int hours = Integer.parseInt(estimatedTimeString[0]);
        int minutes = Integer.parseInt(estimatedTimeString[1]);
        int seconds = Integer.parseInt(estimatedTimeString[2]);

        double secondsToMinutes = (seconds / 60.0);

        float estimatedTime = (hours * 60) + minutes + (float)secondsToMinutes; // in minutes

        String tourInformation = tourInfo;

        // tourRepository.save(new Tour(tourInformation));
        tourRepository.save(new Tour(tourName, tourDescription, fromStart, toFinish, transportType, tourDistance, estimatedTime, tourInformation));
    }

    public List<String> findAll() {
        return tourRepository.findAll()
                .stream()
                .map(Tour::getName)
                .collect(Collectors.toList());
    }

    public List<String> findToursByKeyword(){
        return tourRepository.findToursByKeyword()
                .stream()
                .map(Tour::getName)
                .collect(Collectors.toList());
    }

    public void searchTours(){tourRepository.searchTours();}


    public void deleteTourByKeyword(String keyword){
        tourRepository.deleteTourByKeyword(keyword);
    }

    public void getSelectedItem(String selectedItem){
        tourRepository.getSelectedItem(selectedItem);
    }
}
