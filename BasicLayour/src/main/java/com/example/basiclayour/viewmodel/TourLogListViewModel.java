package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.boot.model.source.spi.MapsIdSource;

import java.util.Map;


public class TourLogListViewModel {
    private final ObservableList<String> tourLogs = FXCollections.observableArrayList();

    private static final Logger logger = LogManager.getLogger(TourLogListViewModel.class);
    private final EventAggregator eventAggregator;
    private final TourLogService tourLogService;
    private final MapService mapService;

    public TourLogListViewModel(
            EventAggregator eventAggregator,
            TourLogService tourLogService,
            MapService mapService

    ) {
        this.eventAggregator = eventAggregator;
        this.tourLogService = tourLogService;
        this.mapService = mapService;

        eventAggregator.addSubscriber(
                Event.NEW_LOG,
                this::onNewLog
        );
        eventAggregator.addSubscriber(
                Event.DELETE_LOG,
                this::onDeleteLog
        );
        eventAggregator.addSubscriber(
                Event.SELECTED_TOUR,
                this::onSelectedTour
        );
        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::onDeletedTourWithLog
        );
    }

    private void onSelectedTour() {
        tourLogs.clear();
        tourLogs.addAll(tourLogService.findAllLogs(mapService.getTmpSelectedItem()));

        if(tourLogs.size() == 0){
            tourLogs.add("No TourLogs found");
        }
    }

    private void onNewLog() {
        tourLogs.clear();
        tourLogs.addAll(tourLogService.findAllLogs(mapService.getTmpSelectedItem()));
    }

    private void onDeleteLog()
    {
        tourLogs.clear();
        tourLogs.addAll(tourLogService.findAllLogs(mapService.getTmpSelectedItem()));

        if(tourLogs.size() == 0){
            tourLogs.add("No TourLogs found");
        }
    }
    private void onDeletedTourWithLog(){
        tourLogs.clear();
    }

    public ObservableList<String> getTourLogs() {
        return tourLogs;
    }

    public ObservableList<String> setDefaultValue(){
        tourLogs.add("No Tours selected - so no TourLogs to display");
        return tourLogs;
    }

    public void deleteTourLog(String selectedTourLog){
        if(!selectedTourLog.equals("No TourLogs found") && !selectedTourLog.equals("No Tours selected - so no TourLogs to display")){
            if(mapService.getTmpSelectedItem() != null){
                tourLogService.deleteTourLogBySelectedTourLog(selectedTourLog, mapService.getTmpSelectedItem());
                logger.info("Deleted Log " + selectedTourLog);
            }

         } else {
            logger.info("Tour not deleted because no log to delete");
        }
    }


}
