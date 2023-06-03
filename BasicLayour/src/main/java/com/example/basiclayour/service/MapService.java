package com.example.basiclayour.service;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

public class MapService {
    public String imagePath;
    private final EventAggregator eventAggregator;

    public MapService(EventAggregator eventAggregator) {
        this.eventAggregator = eventAggregator;
    }

    public void setImagePath(String imagePath) {
        //String tmpString = "/com/example/basiclayour/mapCollection/";
        //tmpString += imagePath;
        //tmpString += ".jpg";

        String tmpString = "file:mapCollection/";
        tmpString += imagePath;
        tmpString += ".jpg";

        this.imagePath = tmpString;
        eventAggregator.publish(Event.SELECTED_TOUR);
    }

    public String getImagePath() {
        return imagePath;
    }



}
