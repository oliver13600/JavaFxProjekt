package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.SearchViewModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

public class SearchView {
    @FXML
    private TextField searchBar;

    private final SearchViewModel searchViewModel;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @FXML
    void initialize() {

        //String[] suggestions = {"Test", "Test2"};

       //TextFields.bindAutoCompletion(searchBar, suggestions);

        searchBar.textProperty().bindBidirectional(searchViewModel.string1Property());


    }


    @FXML
    void searchTour(){searchViewModel.searchTour();}
}

