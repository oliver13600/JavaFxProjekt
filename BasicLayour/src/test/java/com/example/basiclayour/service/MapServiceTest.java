package com.example.basiclayour.service;

import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MapServiceTest {
    private EventAggregator eventAggregator;
    private TourService tourService;
    private MapService mapService;

    @Before
    public void setUp() {
        eventAggregator = mock(EventAggregator.class);
        tourService = mock(TourService.class);
        mapService = new MapService(eventAggregator, tourService);
    }

    @Test
    public void testSetImagePath() {
        // Arrange
        String selectedTour = "Tour1";
        List<String> tourDescriptions = Arrays.asList("description1", "description2");
        when(tourService.getTourDescriptionByName(selectedTour)).thenReturn(tourDescriptions);

        // Act
        mapService.setImagePath(selectedTour);

        // Assert
        String expectedImagePath = "file:mapCollection/description1.jpg";
        assertEquals(expectedImagePath, mapService.getImagePath());
        assertEquals(selectedTour, mapService.getTmpSelectedItem());
        Mockito.verify(eventAggregator, times(1)).publish(Event.SELECTED_TOUR);
        Mockito.verify(tourService, times(1)).getTourDescriptionByName(selectedTour);
    }

    @Test
    public void testGetImagePath() {
        // Arrange
        String expectedImagePath = "file:mapCollection/example.jpg";
        mapService.imagePath = expectedImagePath;

        // Act
        String imagePath = mapService.getImagePath();

        // Assert
        assertEquals(expectedImagePath, imagePath);
    }

    @Test
    public void testGetTmpSelectedItem() {
        // Arrange
        String expectedSelectedItem = "Tour2";
        mapService.tmpSelectedItem = expectedSelectedItem;

        // Act
        String selectedItem = mapService.getTmpSelectedItem();

        // Assert
        assertEquals(expectedSelectedItem, selectedItem);
    }

}