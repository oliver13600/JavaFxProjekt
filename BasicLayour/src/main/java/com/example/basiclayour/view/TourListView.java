package com.example.basiclayour.view;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.viewmodel.TourListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class TourListView {
    @FXML
    private ListView<String> tours;
    @FXML
    private Button deleteTour;
    @FXML
    private Label tourDetails;

    private final TourListViewModel tourListViewModel;

    public TourListView(TourListViewModel tourListViewModel) {
        this.tourListViewModel = tourListViewModel;
    }

    @FXML
    void initialize() {
        tours.setItems(tourListViewModel.getTours());

        tourDetails.textProperty()
                .bindBidirectional(tourListViewModel.tourDetailsProperty());
    }

    @FXML
    void deleteTour() {
        String selectedTour = tours.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            tourListViewModel.deleteTour(selectedTour);
        }
    }

    @FXML
    public void handleMouseClick(){
        String selectedTour = tours.getSelectionModel().getSelectedItem();
        tourListViewModel.getSelectedItem(selectedTour);
    }
}
