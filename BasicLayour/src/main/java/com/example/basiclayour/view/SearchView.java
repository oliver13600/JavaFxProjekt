package com.example.basiclayour.view;

import com.example.basiclayour.viewmodel.SearchViewModel;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import org.controlsfx.control.textfield.TextFields;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SearchView {
    @FXML
    private TextField searchBar;

    private final SearchViewModel searchViewModel;
    private final ContextMenu autocompleteMenu;

    public SearchView(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
        this.autocompleteMenu = new ContextMenu();
    }

    @FXML
    void initialize() {
        searchBar.textProperty().bindBidirectional(searchViewModel.string1Property());

        BooleanBinding hasText = searchBar.textProperty().isEmpty().or(searchBar.textProperty().isNull());
        autocompleteMenu.hide();

        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (hasText.get()) {
                hideSuggestions();
            } else {
                showSuggestions(searchViewModel.getSuggestions(newValue));
            }
        });

        searchBar.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchTour();
            }
        });

        autocompleteMenu.setOnAction(event -> {
            MenuItem selectedItem = (MenuItem) event.getTarget();
            if (selectedItem != null) {
                String suggestion = selectedItem.getText();
                searchBar.setText(suggestion);
                searchBar.positionCaret(suggestion.length());
                hideSuggestions();
            }
        });
    }

    @FXML
    void searchTour() {
        String keyword = searchBar.getText();
        searchViewModel.setString1(keyword);
        searchViewModel.searchTour();
    }



    private void showSuggestions(List<String> suggestions) {
        autocompleteMenu.getItems().clear();
        for (String suggestion : suggestions) {
            MenuItem menuItem = new MenuItem(suggestion);
            autocompleteMenu.getItems().add(menuItem);
        }
        autocompleteMenu.show(searchBar, Side.BOTTOM, 0, 0);
    }

    private void hideSuggestions() {
        autocompleteMenu.hide();
    }
}

