package com.example.basiclayour.service;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.repository.TourRepository;

public class MapService {
    public String imagePath;
    public String tmpSelectedItem = "";
    private final EventAggregator eventAggregator;
    private final TourService tourService;

    public MapService(EventAggregator eventAggregator, TourService tourService) {
        this.eventAggregator = eventAggregator;
        this.tourService = tourService;
    }

    public void setImagePath(String selectedTour) {

        tmpSelectedItem = selectedTour; // for tourLogs

        // Get TourDescription from TourName
        System.out.println("Tourname: " + selectedTour);
        String newImagePath = tourService.getTourDescriptionByName(selectedTour).get(0);
        System.out.println("Resulting Description: " + newImagePath);

        String tmpString = "file:mapCollection/";
        tmpString += newImagePath;
        tmpString += ".jpg";

        this.imagePath = tmpString;
        eventAggregator.publish(Event.SELECTED_TOUR);
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getTmpSelectedItem() {
        return tmpSelectedItem;
    }

}
