package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.SearchViewModel;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchView {
    @FXML
    private TextField textField;

    private final SearchViewModel searchViewModel;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @FXML
    void initialize() {
        textField.textProperty().bindBidirectional(searchViewModel.string1Property());
    }
}
