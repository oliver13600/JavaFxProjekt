package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;

public class MapViewModel {
    //private final ObjectProperty<ImageView> imageView = new SimpleObjectProperty<ImageView>();
    private final ObjectProperty<Image> mapImage = new SimpleObjectProperty<Image>();
    private final MapService mapService;
    private  final EventAggregator eventAggregator;

    private static final Logger logger = LogManager.getLogger(MapViewModel.class);

    public MapViewModel(MapService mapService, EventAggregator eventAggregator){
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

    public void setNewImagePath() {
        String imagePath = mapService.getImagePath();

        String filePath[] = imagePath.split(":");
        File file = new File(filePath[1]); // "mapCollection/Krems-to-Graz.jpg"

        if(file.exists()){
            Image image = new Image(imagePath);
            mapImage.set(image);
        } else {
            Image image = new Image("file:mapCollection/mapError.png");
            mapImage.set(image);
            logger.error("No file found for display - fileNotFound");
        }
    }

    public void deleteImage(){
        String imagePath = mapService.getImagePath();
        String filePath[] = imagePath.split(":");
        File file = new File(filePath[1]);

        if(file.exists()){
            file.delete();
        } else {
            logger.error("File could not be deleted because it does not exist");
        }
    }

    public void setDefaultImage(){
        Image image = new Image("file:mapCollection/map.jpg");
        mapImage.set(image);
    }

    public Image getMapImage() {
        return mapImage.get();
    }

    public ObjectProperty<Image> mapImageProperty() {
        return mapImage;
    }

    public void setMapImage(Image mapImage) {
        this.mapImage.set(mapImage);
    }
}
