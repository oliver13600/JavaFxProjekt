package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TourListViewModel {
    private final ObservableList<String> tours = FXCollections.observableArrayList();
    private final StringProperty tourDetails = new SimpleStringProperty("");

    private final EventAggregator eventAggregator;
    private final TourService tourService;
    private final MapService mapService;
    private final PdfGenerationService pdfGenerationService;
    private final SearchService searchService;



    public TourListViewModel(
            EventAggregator eventAggregator,
            TourService tourService,
            MapService mapservice,
            SearchService searchService,
            PdfGenerationService pdfGenerationService
    ) {
        this.eventAggregator = eventAggregator;
        this.tourService = tourService;
        this.mapService = mapservice;
        this.searchService = searchService;
        this.pdfGenerationService = pdfGenerationService;

        tours.addAll(tourService.findAll());

        eventAggregator.addSubscriber(
                Event.NEW_TOUR,
                this::onNewTour
        );
        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::onDeleteTour
         );
        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::emptyTourDetailInformation
        );

        eventAggregator.addSubscriber(
                Event.SEARCH_TOUR,
                this::onSearchTour
        );
        eventAggregator.addSubscriber(
                Event.SELECTED_TOUR,
                this::getTourDetailInformation
        );
    }

    private void onNewTour() {
        tours.clear();
        tours.addAll(tourService.findAll());
    }

    private void onDeleteTour()
    {
        tours.clear();
        tours.addAll(tourService.findAll());
    }

    private void onSearchTour()
    {
        tours.clear();
        tours.addAll(searchService.findToursByKeyword());
    }

    public ObservableList<String> getTours() {
        return tours;
    }

    public void deleteTour(String keyword){
        tourService.deleteTourByKeyword(keyword);
    }

    public void getSelectedItem(String selectedItem){
        mapService.setImagePath(selectedItem);
        pdfGenerationService.setTourToPdf(selectedItem);

    }

    public String getTourDetails() {
        return tourDetails.get();
    }

    public StringProperty tourDetailsProperty() {
        return tourDetails;
    }

    public void setTourDetails(String tourDetails) {
        this.tourDetails.set(tourDetails);
    }

    public void getTourDetailInformation(){
        String detailString = tourService.getTourInformationForDetails(mapService.getTmpSelectedItem());
        String detailStringSplit = detailString.substring(5, detailString.length());
        String splitBracket[] = detailStringSplit.split("\\)");
        String finalTourDetailString = splitBracket[0] + ")\n" + splitBracket[1];
        tourDetails.set(finalTourDetailString);
    }

    public void emptyTourDetailInformation(){
        tourDetails.set("");
    }
}
