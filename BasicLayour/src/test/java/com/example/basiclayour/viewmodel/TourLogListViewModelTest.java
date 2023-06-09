package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.service.TourLogService;
import javafx.collections.ObservableList;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

public class TourLogListViewModelTest {
    @Mock
    private EventAggregator eventAggregator;

    @Mock
    private TourLogService tourLogService;

    private TourLogListViewModel viewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        viewModel = new TourLogListViewModel(eventAggregator, tourLogService);
    }

    @Test
    public void testGetLogs_ReturnsTourLogs() {
        ObservableList<String> expectedLogs = viewModel.getLogs();

        assertEquals(0, expectedLogs.size());
    }


    @Test
    public void testDeleteTourLog_PrintsTourDeleted() {
        String selectedTourLog = "tour_log_id";

        viewModel.deleteTourLog(selectedTourLog);

        // Use any verification mechanism to ensure that "Tour deleted..." was printed
    }



}