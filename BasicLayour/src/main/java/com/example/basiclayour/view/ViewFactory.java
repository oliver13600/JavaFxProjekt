package com.example.basiclayour.view;


import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.repository.TourRepository;
import com.example.basiclayour.service.TourService;
import com.example.basiclayour.viewmodel.AddTourViewModel;
import com.example.basiclayour.viewmodel.SearchViewModel;

public class ViewFactory {

    private static ViewFactory instance;

    private final EventAggregator eventAggregator;

    private final HibernateSessionFactory sessionFactory;

    private final TourRepository tourRepository;

    private final TourService tourService;

    private final SearchViewModel searchViewModel;
    private final AddTourViewModel addTourViewModel;

    private ViewFactory() {
        eventAggregator = new EventAggregator();
        sessionFactory = new HibernateSessionFactory();

        tourRepository = new TourRepository(sessionFactory, eventAggregator);
        tourService = new TourService(tourRepository);

        addTourViewModel = new AddTourViewModel(tourService);
        searchViewModel = new SearchViewModel();
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

        throw new IllegalArgumentException();
    }

    public static ViewFactory getInstance() {
        if (null == instance) {
            instance = new ViewFactory();
        }
        return instance;
    }
}
