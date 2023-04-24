package com.example.basiclayour.view;


import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.viewmodel.SearchViewModel;

public class ViewFactory {

    private static ViewFactory instance;

    private final EventAggregator eventAggregator;

    private final SearchViewModel searchViewModel;

    private ViewFactory() {
        eventAggregator = new EventAggregator();
        searchViewModel = new SearchViewModel();
    }

    public Object create(Class<?> viewClass) {
        if (viewClass == MainView.class) {
            return new MainView();
        }
        if (viewClass == SearchView.class) {
            return new SearchView(searchViewModel);
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
