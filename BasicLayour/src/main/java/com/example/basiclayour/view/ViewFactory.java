package com.example.basiclayour.view;


import com.example.basiclayour.event.EventAggregator;

public class ViewFactory {

    private static ViewFactory instance;

    private final EventAggregator eventAggregator;


    private ViewFactory() {
        eventAggregator = new EventAggregator();
    }

    public Object create(Class<?> viewClass) {
        if (viewClass == MainView.class) {
            return new MainView();
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
