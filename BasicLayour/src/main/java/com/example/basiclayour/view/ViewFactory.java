package com.example.basiclayour.view;


import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.repository.TourLogRepository;
import com.example.basiclayour.service.ConfigurationService;
import com.example.basiclayour.service.PropertiesFileService;
import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.service.PdfGenerationService;
import com.example.basiclayour.service.iTextPdfGenerationService;
import com.example.basiclayour.repository.TourRepository;
import com.example.basiclayour.service.*;
import com.example.basiclayour.viewmodel.*;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class ViewFactory {
    private static ViewFactory instance;
    private final EventAggregator eventAggregator;
    private final HibernateSessionFactory sessionFactory;
    private final TourRepository tourRepository;
    private final TourLogRepository tourLogRepository;
    private final RouteService routeService;
    private final PdfGenerationService pdfGenerationService;
    private final TourService tourService;
    private final TourLogService tourLogService;
    private final SearchService searchService;
    private final ConfigurationService configurationService;
    private final SearchViewModel searchViewModel;
    private final MapViewModel mapViewModel;
    private final MenuBarViewModel menuBarViewModel;
    private final AddTourViewModel addTourViewModel;
    private final AddTourLogViewModel addTourLogViewModel;
    private final TourListViewModel tourListViewModel;
    private final TourLogListViewModel tourLogListViewModel;
    private final MapService mapService;

    private ViewFactory() {
        eventAggregator = new EventAggregator();

        sessionFactory = new HibernateSessionFactory();

        tourRepository = new TourRepository(sessionFactory, eventAggregator);
        tourLogRepository = new TourLogRepository(sessionFactory, eventAggregator);

        tourService = new TourService(tourRepository);
        searchService = new SearchService(tourRepository);

        tourLogService = new TourLogService(tourLogRepository, tourRepository);

        configurationService = new PropertiesFileService();

        mapService = new MapService(eventAggregator, tourService);
        routeService = new MapQuestRouteService(configurationService);
        pdfGenerationService = new iTextPdfGenerationService(tourService);

        mapViewModel = new MapViewModel(mapService, eventAggregator);
        menuBarViewModel = new MenuBarViewModel(pdfGenerationService);
        addTourViewModel = new AddTourViewModel(routeService, tourService);
        addTourLogViewModel = new AddTourLogViewModel(tourLogService, mapService);
        searchViewModel = new SearchViewModel(tourService, searchService);
        tourListViewModel = new TourListViewModel(eventAggregator, tourService, mapService, searchService, pdfGenerationService);
        tourLogListViewModel = new TourLogListViewModel(eventAggregator, tourLogService, mapService);
    }

    public Object create(Class<?> viewClass) {
        if (viewClass == MainView.class) {
            return new MainView();
        }
        if (viewClass == SearchView.class) {
            return new SearchView(searchViewModel);
        }
        if(viewClass == AddTourView.class){
            return new AddTourView(addTourViewModel);
        }
        if(viewClass == TourListView.class){
            return new TourListView(tourListViewModel);
        }
        if(viewClass == MapView.class){
            return new MapView(mapViewModel);
        }
        if(viewClass == MenuBarView.class){
            return new MenuBarView(menuBarViewModel);
        }
        if(viewClass == AddTourLogView.class){
            return new AddTourLogView(addTourLogViewModel);
        }
        if(viewClass == TourLogListView.class){
            return new TourLogListView(tourLogListViewModel);
        }

        throw new IllegalArgumentException();
    }

    public static ViewFactory getInstance() {
        if (null == instance) {
            instance = new ViewFactory();
        }
        return instance;
    }
}
