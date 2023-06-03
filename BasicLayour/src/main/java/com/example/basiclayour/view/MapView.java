package com.example.basiclayour.view;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;

public class MapView {
    @FXML
    private ImageView imageView;
    @FXML
    private Image mapImage;
    private final MapService mapService;
    private  final EventAggregator eventAggregator;

    public MapView(MapService mapService ,EventAggregator eventAggregator) {
        this.mapService = mapService;
        this.eventAggregator = eventAggregator;

        eventAggregator.addSubscriber(
                Event.SELECTED_TOUR,
                this::setNewImagePath
        );
    }

    @FXML
    void initialize() {
        mapImage = new Image(getClass().getResourceAsStream("/com/example/basiclayour/mapCollection/map.jpg"));
        imageView.setImage(mapImage);
    }

   public void setNewImagePath() {
       String imagePath = mapService.getImagePath();

       if (getClass().getResourceAsStream(imagePath) != null) {
           mapImage = new Image(getClass().getResourceAsStream(imagePath));
           imageView.setImage(mapImage);
       } else {
            throw new RuntimeException("File does not exist...yet :(");
       }
   }

}
