package com.example.basiclayour.view;


import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.repository.TourRepository;
import com.example.basiclayour.service.MapQuestRouteService;
import com.example.basiclayour.service.MapService;
import com.example.basiclayour.service.RouteService;
import com.example.basiclayour.service.TourService;
import com.example.basiclayour.viewmodel.AddTourViewModel;
import com.example.basiclayour.viewmodel.SearchViewModel;
import com.example.basiclayour.viewmodel.TourListViewModel;

public class ViewFactory {

    private static ViewFactory instance;

    private final EventAggregator eventAggregator;

    private final HibernateSessionFactory sessionFactory;

    private final TourRepository tourRepository;

    private final RouteService routeService;

    private final TourService tourService;
    private final SearchViewModel searchViewModel;

    private final AddTourViewModel addTourViewModel;
    private final TourListViewModel tourListViewModel;

    private final MapService mapService;

    private ViewFactory() {
        eventAggregator = new EventAggregator();
        sessionFactory = new HibernateSessionFactory();

        tourRepository = new TourRepository(sessionFactory, eventAggregator);
        tourService = new TourService(tourRepository);

        mapService = new MapService(eventAggregator);

        routeService = new MapQuestRouteService();

        addTourViewModel = new AddTourViewModel(routeService, tourService);
        searchViewModel = new SearchViewModel(tourService);

        tourListViewModel = new TourListViewModel(eventAggregator, tourService, mapService);
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
            return new MapView(mapService,eventAggregator);
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
