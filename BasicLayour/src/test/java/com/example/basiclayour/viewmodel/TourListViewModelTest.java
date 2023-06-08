package com.example.basiclayour.viewmodel;

import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.MapService;
import com.example.basiclayour.service.PdfGenerationService;
import com.example.basiclayour.service.SearchService;
import com.example.basiclayour.service.TourService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TourListViewModelTest {
    private TourListViewModel tourListViewModel;

    @Mock
    private EventAggregator eventAggregator;

    @Mock
    private TourService tourService;

    @Mock
    private MapService mapService;

    @Mock
    private PdfGenerationService pdfGenerationService;

    @Mock
    private SearchService searchService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        tourListViewModel = new TourListViewModel(eventAggregator, tourService, mapService, searchService, pdfGenerationService);
    }


}