package com.example.basiclayour.model;


import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

import java.util.ArrayList;
import java.util.List;

public class TourRepository {

    private final List<String> words;

    private final EventAggregator eventAggregator;

    public TourRepository(EventAggregator eventAggregator) {
        this.eventAggregator = eventAggregator;
        words = new ArrayList<>();
    }


    public void save(String word) {
        words.add(word);

        eventAggregator.publish(Event.NEW_ROUTE);
    }

    public List<String> findAll() {
        return words;
    }
}