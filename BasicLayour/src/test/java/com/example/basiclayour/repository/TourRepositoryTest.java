package com.example.basiclayour.repository;

import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.Tour;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TourRepositoryTest {

    private TourRepository tourRepository;
    private HibernateSessionFactory sessionFactory;
    private EventAggregator eventAggregator;

    @Before
    public void setUp() {
        // Create mock dependencies
        sessionFactory = mock(HibernateSessionFactory.class);
        eventAggregator = mock(EventAggregator.class);

        // Create an instance of TourRepository with mock dependencies
        tourRepository = new TourRepository(sessionFactory, eventAggregator);
    }

    @Test
    public void testSave_ShouldPersistTourAndPublishEvent() {
        // Arrange
        Tour tour = new Tour();

        Session session = mock(Session.class);
        Transaction transaction = mock(Transaction.class);
        when(sessionFactory.openSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);

        // Act
        tourRepository.save(tour);

        // Assert
        verify(session).persist(tour);
        verify(transaction).commit();
        verify(eventAggregator).publish(Event.NEW_TOUR);
    }


    @Test
    public void testSearchTours_ShouldPublishSearchTourEvent() {
        // Act
        tourRepository.searchTours();

        // Assert
        verify(eventAggregator).publish(Event.SEARCH_TOUR);
    }



}