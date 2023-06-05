package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MapViewModel {
    //private final ObjectProperty<ImageView> imageView = new SimpleObjectProperty<ImageView>();
    private final ObjectProperty<Image> mapImage = new SimpleObjectProperty<Image>();
    private final MapService mapService;
    private  final EventAggregator eventAggregator;

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
