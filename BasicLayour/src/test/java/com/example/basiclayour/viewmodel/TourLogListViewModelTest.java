package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.service.MapService;
import com.example.basiclayour.service.TourLogService;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TourLogListViewModelTest {
    @Mock
    private EventAggregator eventAggregator;

    @Mock
    private TourLogService tourLogService;

    @Mock
    private MapService mapService;

    private TourLogListViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new TourLogListViewModel(eventAggregator, tourLogService, mapService);
    }



    @Test
    public void testSetDefaultValue() {
        // Act
        viewModel.setDefaultValue();

        // Assert
        assertEquals(Collections.singletonList("No Tours selected - so no TourLogs to display"), viewModel.getTourLogs());
    }

    @Test
    public void testDeleteTourLog() {
        // Act
        viewModel.deleteTourLog("SelectedTourLog");

        // Assert
        // You can add assertions here based on the expected behavior of the deleteTourLog() method
    }



}