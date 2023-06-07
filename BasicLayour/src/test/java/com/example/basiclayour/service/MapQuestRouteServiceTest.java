package com.example.basiclayour.service;

import com.example.basiclayour.dto.Route;
import com.example.basiclayour.viewmodel.SearchViewModel;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MapQuestRouteServiceTest {

    private ConfigurationService configurationService;

    private MapQuestRouteService routeService;

    @BeforeEach
    public void setUp() {

    }




    @Test
    public void testGetRouteType() {

        configurationService = mock(PropertiesFileService.class);

        routeService = new MapQuestRouteService(configurationService);

        // Test case inputs
        String carSelection = "Car";
        String bicycleSelection = "Bicycle";
        String walkingSelection = "Walking";
        String unknownSelection = "Unknown";

        // Invoke the method and verify the results
        assertEquals("fastest", routeService.getRouteType(carSelection));
        assertEquals("bicycle", routeService.getRouteType(bicycleSelection));
        assertEquals("pedestrian", routeService.getRouteType(walkingSelection));
        assertEquals("fastest", routeService.getRouteType(unknownSelection));
    }

}