package com.example.basiclayour.view;


import com.example.basiclayour.viewmodel.AddTourLogViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTourLogView {

    @FXML
    private DatePicker logDate;

    @FXML
    private TextField hours;

    @FXML
    private TextField minutes;

    @FXML
    private TextField logComment;

    @FXML
    private TextField logDifficulty;

    @FXML
    private TextField totalTime;

    @FXML
    private ChoiceBox<String> choiceBoxRating;

    @FXML
    private Label output;

    private final AddTourLogViewModel addTourLogViewModel;

    public AddTourLogView(AddTourLogViewModel addTourLogViewModel) {
        this.addTourLogViewModel = addTourLogViewModel;
    }

    @FXML
    void initialize() {
        logDate.valueProperty()
                .bindBidirectional(addTourLogViewModel.logDateProperty());
        minutes.textProperty()
                        .bindBidirectional(addTourLogViewModel.minutesProperty());
        hours.textProperty()
                        .bindBidirectional(addTourLogViewModel.hoursProperty());
        logComment.textProperty()
                .bindBidirectional(addTourLogViewModel.logCommentProperty());
        logDifficulty.textProperty()
                .bindBidirectional(addTourLogViewModel.logDifficultyProperty());
        totalTime.textProperty()
                .bindBidirectional(addTourLogViewModel.totalTimeProperty());
        output.textProperty()
                .bind(addTourLogViewModel.outputProperty());

        choiceBoxRating.setItems(addTourLogViewModel.getRatingChoiceBoxInput());
        choiceBoxRating.setValue(addTourLogViewModel.setRatingChoiceBoxInput());
    }

    @FXML
    private void addTourLog() {
        String selectedRating = choiceBoxRating.getSelectionModel().getSelectedItem();

        addTourLogViewModel.addTourLog(selectedRating);

    }

}

