package com.example.basiclayour.service;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.repository.TourLogRepository;
import com.example.basiclayour.repository.TourRepository;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TourLogService {

    private final TourLogRepository tourLogRepository;
    private final TourRepository tourRepository;

    public TourLogService(TourLogRepository tourLogRepository, TourRepository tourRepository){
        this.tourLogRepository = tourLogRepository;
        this.tourRepository = tourRepository;
    }

    public void save(String logInfo, String selectedTour){

        String tourLogInforSplit[] = logInfo.split(";");

        // LocalDateTime
        String dateString = tourLogInforSplit[1];
        String dateStringSplit[] = dateString.split(":");
        String removeDateBracketSplit[] = dateStringSplit[1].split("]");
        String removeFirstCharacterDateString[] = removeDateBracketSplit[0].split(" ");
        String finalDateString[] = removeFirstCharacterDateString[1].split("-");

        String hourString = tourLogInforSplit[2];
        String hourStringSplit[] = hourString.split(" ");

        String minuteString = tourLogInforSplit[3];
        String minuteStringSplit[] = minuteString.split(" ");

        LocalDateTime dateTime = LocalDateTime.of(Integer.parseInt(finalDateString[0]), Integer.parseInt(finalDateString[1]), Integer.parseInt(finalDateString[2]), Integer.parseInt(hourStringSplit[0]), Integer.parseInt(minuteStringSplit[0]));

        // Comment
        String commentString = tourLogInforSplit[4];
        String commentStringSplit[] = commentString.split("\\|");


        // Difficulty
        String difficultyString = tourLogInforSplit[5];
        String difficultyStringSplit[] = difficultyString.split(" ");
        Integer difficultyInteger = convertDifficultyToInt(difficultyStringSplit[0]);


        // total time
        String totalTimeString = tourLogInforSplit[6];
        String totalTimeStringSplit[] = totalTimeString.split("\\|");

        // total time from string to float => save as minutes
        String totalTimeStringForFloat[] = totalTimeStringSplit[0].split(":");
        int hours = Integer.parseInt(totalTimeStringForFloat[0]);
        int minutes = Integer.parseInt(totalTimeStringForFloat[1]);
        int seconds = Integer.parseInt(totalTimeStringForFloat[2]);

        double secondsToMinutes = (seconds / 60.0);

        float totalTime = (hours * 60) + minutes + (float)secondsToMinutes; // in minutes

        // Rating
        String ratingString = tourLogInforSplit[7];
        String ratingStringSplit[] = ratingString.split(" ");

        System.out.println(dateTime
        + " " + commentStringSplit[0] + " " +difficultyInteger + difficultyInteger
                + " " + totalTime + " " + ratingStringSplit[0]);

        System.out.println(" SELEECTED TOUR NAME: " + selectedTour);

        Tour tour = tourRepository.findTourByName(selectedTour);

        // LocalDateTime dateTime, Integer difficulty, float totalTime, int rating, Tour tour
        if(tour != null){
            tourLogRepository.save(new TourLog(dateTime, commentStringSplit[0] ,difficultyInteger, totalTime, Integer.parseInt(ratingStringSplit[0]), tour));
        }
    }

    public int convertDifficultyToInt(String difficultyString){
        switch (difficultyString.toLowerCase()){
            case "beginner":
                return 1;
            case "easy":
                return 2;
            case "medium":
                return 3;
            case "hard":
                return 4;
            case "extreme":
                return 5;
            default:
                return 0;
        }
    }

    public List<String> getTourLogInformation(String tourName){
        return tourLogRepository.findTourLogsByTour(tourName)
                .stream()
                .map(TourLog::getTourLogInformation)
                .collect(Collectors.toList());
    }

    public List<String> findAllLogs() {
        return tourLogRepository.findAll()
                .stream()
                .map(TourLog::getTourLogInformation)
                .collect(Collectors.toList());
    }

}
