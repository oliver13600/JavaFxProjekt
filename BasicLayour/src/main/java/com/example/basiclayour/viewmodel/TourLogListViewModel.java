package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TourLogListViewModel {

    private final EventAggregator eventAggregator;
    private final TourLogService tourLogService;
    private final ObservableList<String> tourLogs = FXCollections.observableArrayList();

    private final ObservableList<TourLog> tourLogList = FXCollections.observableArrayList(
            new TourLog(getParsedTime(), "Das ist mein Comment", 1, 43, 1)
    );

    public LocalDateTime getParsedTime(){
        String dateString = "2023-04-04 12:12:12";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss", Locale.ROOT);
        LocalDateTime parsedDate = LocalDateTime.parse(dateString, formatter);

        return parsedDate;
    }

    public TourLogListViewModel(
            EventAggregator eventAggregator,
            TourLogService tourLogService
    ) {
        this.eventAggregator = eventAggregator;
        this.tourLogService = tourLogService;


        //tourLogs.addAll(tourLogService.findAllLogs());

        eventAggregator.addSubscriber(
                Event.NEW_TOUR,
                this::onNewLog
        );
        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::onDeleteLog
        );
    }

    private void onNewLog() {
        tourLogs.clear();
        //tourLogs.addAll(tourLogService.findAllLogs());
    }

    private void onDeleteLog()
    {
        tourLogs.clear();
        //tourLogs.addAll(tourLogService.findAllLogs());
    }

    public ObservableList<String> getLogs() {
        return tourLogs;
    }

    public ObservableList<TourLog> getTourLogs(){
        return tourLogList;
    }

    public void deleteTour(String keyword){
        //tourLogService.deleteLogByKeyword(keyword);
    }

    public void deleteTourLog(String selectedTourLog){
        System.out.println("Tour deleted...");
    }


}
