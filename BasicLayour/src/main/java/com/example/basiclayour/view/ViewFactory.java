package com.example.basiclayour.view;


import com.example.basiclayour.configuration.ConfigurationService;
import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.pdfGeneration.PdfGenerationService;
import com.example.basiclayour.repository.TourRepository;
import com.example.basiclayour.service.*;
import com.example.basiclayour.viewmodel.*;

public class ViewFactory {
    private static ViewFactory instance;
    private final EventAggregator eventAggregator;
    private final HibernateSessionFactory sessionFactory;
    private final TourRepository tourRepository;
    private final RouteService routeService;
    private final TourService tourService;
    private final SearchService searchService;
    private final PdfGenerationService pdfGenerationService;
    private final ConfigurationService configurationService;
    private final SearchViewModel searchViewModel;
    private final MapViewModel mapViewModel;
    private final MenuBarViewModel menuBarViewModel;
    private final AddTourViewModel addTourViewModel;
    private final TourListViewModel tourListViewModel;
    private final MapService mapService;

    private ViewFactory() {
        eventAggregator = new EventAggregator();

        sessionFactory = new HibernateSessionFactory();

        tourRepository = new TourRepository(sessionFactory, eventAggregator);

        tourService = new TourService(tourRepository);
        searchService = new SearchService(tourRepository);
        configurationService = new ConfigurationService();
        pdfGenerationService = new PdfGenerationService(tourService);
        mapService = new MapService(eventAggregator);
        routeService = new MapQuestRouteService(configurationService);

        mapViewModel = new MapViewModel(mapService, eventAggregator);
        menuBarViewModel = new MenuBarViewModel(pdfGenerationService);
        addTourViewModel = new AddTourViewModel(routeService, tourService);
        searchViewModel = new SearchViewModel(tourService, searchService);
        tourListViewModel = new TourListViewModel(eventAggregator, tourService, mapService, searchService);
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

        throw new IllegalArgumentException();
    }

    public static ViewFactory getInstance() {
        if (null == instance) {
            instance = new ViewFactory();
        }
        return instance;
    }
}
