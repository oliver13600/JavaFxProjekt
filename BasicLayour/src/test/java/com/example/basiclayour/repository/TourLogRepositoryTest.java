package com.example.basiclayour.repository;

import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TourLogRepositoryTest {

    @Mock
    private HibernateSessionFactory sessionFactory;

    @Mock
    private EventAggregator eventAggregator;

    @Mock
    private Session session;

    private TourLogRepository repository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        repository = new TourLogRepository(sessionFactory, eventAggregator);
    }




}