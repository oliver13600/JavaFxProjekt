package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import javax.imageio.ImageIO;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class MapViewModelTest {

    private static final String TEST_IMAGE_PATH = "mapCollection/test-image.png";
    private static final String NON_EXISTING_IMAGE_PATH = "mapCollection/non-existing-image.png";
    private static final String DEFAULT_IMAGE_PATH = "mapCollection/map.jpg";

    private MapViewModel mapViewModel;
    private MapService mapService;
    private EventAggregator eventAggregator;


    @BeforeEach
    public void setup() {
        mapService = mock(MapService.class);
        eventAggregator = mock(EventAggregator.class);
        mapViewModel = new MapViewModel(mapService, eventAggregator);
    }




}