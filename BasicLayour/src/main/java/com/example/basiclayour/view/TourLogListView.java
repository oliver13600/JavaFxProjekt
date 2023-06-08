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

    private final TourLogListViewModel tourLogListViewModel;

    public TourLogListView(TourLogListViewModel tourLogListViewModel){
        this.tourLogListViewModel = tourLogListViewModel;
    }
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Button deleteLog;
    @FXML
    private TableView<TourLog> tourLogs;
    @FXML
    private TableColumn<TourLog, LocalDateTime> dateTime;
    @FXML
    private TableColumn<TourLog, String> comment;
    @FXML
    private TableColumn<TourLog, Integer> difficulty;
    @FXML
    private TableColumn<TourLog, Float> totalTime;
    @FXML
    private TableColumn<TourLog, Integer> rating;

    public void initialize() {
        //dateTime.setCellValueFactory(new PropertyValueFactory<TourLog, LocalDateTime>("Date"));
        comment.setCellValueFactory(new PropertyValueFactory<TourLog, String>("Comment"));
        difficulty.setCellValueFactory(new PropertyValueFactory<TourLog, Integer>("Difficulty"));
        totalTime.setCellValueFactory(new PropertyValueFactory<TourLog, Float>("TotalTime"));
        rating.setCellValueFactory(new PropertyValueFactory<TourLog, Integer>("Rating"));

        tourLogs.setItems(tourLogListViewModel.getTourLogs());
    }

    @FXML
    void deleteTourLog() {
        String selectedTour = String.valueOf(tourLogs.getSelectionModel().getSelectedItem());
        if (selectedTour != null) {
            tourLogListViewModel.deleteTourLog(selectedTour);
        }
    }

}
