package com.example.basiclayour.service;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.repository.TourRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MapService {
    public String imagePath;
    public String tmpSelectedItem = "";
    private final EventAggregator eventAggregator;
    private final TourService tourService;
    private static final Logger logger = LogManager.getLogger(MapService.class);

    public MapService(EventAggregator eventAggregator, TourService tourService) {
        this.eventAggregator = eventAggregator;
        this.tourService = tourService;
    }

    public void setImagePath(String selectedTour) {

        tmpSelectedItem = selectedTour; // for tourLogs

        if(tmpSelectedItem != null){
            // Get TourDescription from TourName
            logger.info("TourName: " + selectedTour);
            String newImagePath = tourService.getTourDescriptionByName(selectedTour).get(0);
            logger.info("Resulting Description: " + newImagePath);

            String tmpString = "file:mapCollection/";
            tmpString += newImagePath;
            tmpString += ".jpg";

            this.imagePath = tmpString;
            eventAggregator.publish(Event.SELECTED_TOUR);
        } else {
            logger.info("No tour selected");
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTmpSelectedItem() {
        return tmpSelectedItem;
    }

    public boolean checkIfImageUsedByOtherTour(String tourToCheck){

        if(tourService.getAmountOfMatches(tourToCheck) == 0){
            logger.info(tourService.getAmountOfMatches(tourToCheck) + " amount of matches to: " + tourToCheck);
            return false;
        } else {
            logger.info(tourService.getAmountOfMatches(tourToCheck) + " amount of matches to: " + tourToCheck);
            return true;
        }
    }

}
