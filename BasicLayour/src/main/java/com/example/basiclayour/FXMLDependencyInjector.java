package com.example.basiclayour;

import com.example.basiclayour.view.ViewFactory;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;


public class FXMLDependencyInjector {

    public static Parent load(String location, Locale locale) throws IOException {
        FXMLLoader loader = getLoader(location, locale);
        return loader.load();
    }

    public static FXMLLoader getLoader(String location, Locale locale) {
        return new FXMLLoader(
                FXMLDependencyInjector.class.getResource("/com/example/basiclayour/" + location),
                ResourceBundle.getBundle("com.example.basiclayour.gui", locale),
                new JavaFXBuilderFactory(),
                viewClass -> ViewFactory.getInstance().create(viewClass)
        );
    }
}