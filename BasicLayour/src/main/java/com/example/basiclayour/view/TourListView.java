package com.example.basiclayour.view;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.viewmodel.TourListViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class TourListView {
    @FXML
    private ListView<String> tours;

    private final TourListViewModel tourListViewModel;

    public TourListView(TourListViewModel tourListViewModel) {
        this.tourListViewModel = tourListViewModel;
    }

    @FXML
    void initialize() {
        tours.setItems(tourListViewModel.getTours());
    }
}
