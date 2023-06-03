package com.example.basiclayour.view;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
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
    private final MapService mapService;
    private  final EventAggregator eventAggregator;

    public MapView(MapService mapService, EventAggregator eventAggregator) {
        this.mapService = mapService;
        this.eventAggregator = eventAggregator;

        eventAggregator.addSubscriber(
                Event.SELECTED_TOUR,
                this::setNewImagePath
        );

        eventAggregator.addSubscriber(
                Event.DELETE_TOUR,
                this::deleteImage
        );
    }

    @FXML
    void initialize() {
        mapImage = new Image("file:mapCollection/map.jpg");
        imageView.setImage(mapImage);
    }

   public void setNewImagePath() {
       String imagePath = mapService.getImagePath();

       String filePath[] = imagePath.split(":");
       File file = new File(filePath[1]); // "mapCollection/Krems-to-Graz.jpg"

       if(file.exists()){
           mapImage = new Image(imagePath);
           imageView.setImage(mapImage);
       } else {
           mapImage = new Image("file:mapCollection/mapError.png");
           imageView.setImage(mapImage);
           System.out.println("No file found for display");
          //throw new RuntimeException("File does not exist");
       }
   }

   public void deleteImage(){
       String imagePath = mapService.getImagePath();
       String filePath[] = imagePath.split(":");
       File file = new File(filePath[1]);

       if(file.exists()){
            file.delete();
       } else {
           System.out.println("File could not be deleted because it does not exist");
       }
   }

}
