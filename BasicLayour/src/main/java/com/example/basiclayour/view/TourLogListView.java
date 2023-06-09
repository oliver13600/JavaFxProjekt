package com.example.basiclayour.view;

import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.viewmodel.TourLogListViewModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TourLogListView {
    @FXML
    private Button deleteLog;
    @FXML
    private ListView<String> tourLogs;

    private final TourLogListViewModel tourLogListViewModel;

    public TourLogListView(TourLogListViewModel tourLogListViewModel){
        this.tourLogListViewModel = tourLogListViewModel;
    }


    public void initialize() {
        tourLogs.setItems(tourLogListViewModel.setDefaultValue());
    }

    @FXML
    void deleteTourLog() {
        String selectedTour = tourLogs.getSelectionModel().getSelectedItem();
        if (selectedTour != null) {
            tourLogListViewModel.deleteTourLog(selectedTour);
        }
    }

}

