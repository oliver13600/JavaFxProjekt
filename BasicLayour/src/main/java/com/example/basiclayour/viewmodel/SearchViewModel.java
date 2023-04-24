package com.example.basiclayour.viewmodel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SearchViewModel {
    private final StringProperty string1 = new SimpleStringProperty("");

    public StringProperty string1Property(){return string1;}

}
