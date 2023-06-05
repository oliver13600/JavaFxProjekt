package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.AddTourViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTourView {

    @FXML
    private TextField textField1;

    @FXML
    private TextField textField2;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private Label output;

    private final AddTourViewModel addTourViewModel;

    public AddTourView(AddTourViewModel addTourViewModel) {
        this.addTourViewModel = addTourViewModel;
    }

    @FXML
    void initialize() {
        textField1.textProperty()
                .bindBidirectional(addTourViewModel.string1Property());
        textField2.textProperty()
                .bindBidirectional(addTourViewModel.string2Property());
        output.textProperty()
                .bind(addTourViewModel.outputProperty());
    }

    @FXML
    private void addTour() {
        addTourViewModel.addTour();
    }

}
