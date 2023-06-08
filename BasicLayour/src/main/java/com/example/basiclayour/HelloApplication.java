package com.example.basiclayour;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Locale;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLDependencyInjector.load("main-view.fxml", Locale.ENGLISH);
        Scene scene = new Scene(root, 1400,500);
        stage.getIcons().add(new Image("file:mapCollection/mapIcon.png"));
        stage.setTitle("Tour Planer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}