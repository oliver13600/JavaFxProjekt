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

        String tourName = tourInformationSplit[1];

        String fromStart = tourInformationSplit[3];
        String toFinish = tourInformationSplit[5];

        String tourDescription = tourInformationSplit[3] + "-" + tourInformationSplit[4] + "-" + tourInformationSplit[5];

        String tourDistanceString = tourInformationSplit[8].replaceAll("[\\p{Ps}\\p{Pe}]"," ");
        float tourDistance = Float.parseFloat(tourDistanceString);

        String estimatedTimeString[] = tourInformationSplit[7].split(":");
        int hours = Integer.parseInt(estimatedTimeString[0]);
        int minutes = Integer.parseInt(estimatedTimeString[1]);
        int seconds = Integer.parseInt(estimatedTimeString[2]);

        double secondsToMinutes = (seconds / 60.0);

        float estimatedTime = (hours * 60) + minutes + (float)secondsToMinutes; // in minutes

        String transportType = tourInformationSplit[10];

        String tourInformation = tourInfo;

        tourRepository.save(new Tour(tourName, tourDescription, fromStart, toFinish, transportType, tourDistance, estimatedTime, tourInformation));
    }

    public List<String> findAll() {
        return tourRepository.findAll()
                .stream()
                .map(Tour::getTourDescription)
                .collect(Collectors.toList());
    }

    public List<String> findToursByKeyword(){
        return tourRepository.findToursByKeyword()
                .stream()
                .map(Tour::getTourDescription)
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
