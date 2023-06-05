package com.example.basiclayour.view;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import com.example.basiclayour.viewmodel.MapViewModel;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.net.*;
import java.nio.file.FileSystem;


public class MapView {
    @FXML
    private ImageView imageView;
    @FXML
    private Image mapImage;

    private  final MapViewModel mapViewModel;

    public MapView(MapViewModel mapViewModel) {
        this.mapViewModel = mapViewModel;
    }

    @FXML
    void initialize() {
        imageView.imageProperty().bindBidirectional(mapViewModel.mapImageProperty());
        mapViewModel.setDefaultImage();
    }



}
