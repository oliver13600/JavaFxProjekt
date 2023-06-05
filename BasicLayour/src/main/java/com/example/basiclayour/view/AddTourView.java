package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.AddTourViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddTourView {

    @FXML
    private TextField tourName;

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
        tourName.textProperty()
                .bindBidirectional(addTourViewModel.tourNameProperty());
        textField1.textProperty()
                .bindBidirectional(addTourViewModel.string1Property());
        textField2.textProperty()
                .bindBidirectional(addTourViewModel.string2Property());
        output.textProperty()
                .bind(addTourViewModel.outputProperty());

        choiceBox.setValue(addTourViewModel.setDefaultValue());
        choiceBox.setItems(addTourViewModel.getChoiceBoxInputs());
    }

    @FXML
    private void addTour() {
        String selectedChoice = choiceBox.getSelectionModel().getSelectedItem();
        addTourViewModel.addTour(selectedChoice);
    }

}
