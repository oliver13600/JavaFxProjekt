package com.example.basiclayour.repository;


import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

import java.util.List;
import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.model.Tour;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;

public class TourRepository {
    private final HibernateSessionFactory sessionFactory;
    private final EventAggregator eventAggregator;

    public TourRepository(
            HibernateSessionFactory sessionFactory,
            EventAggregator eventAggregator
    ) {
        this.sessionFactory = sessionFactory;
        this.eventAggregator = eventAggregator;
    }


    public void save(Tour tour) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(tour);
            session.getTransaction().commit();
        }

        eventAggregator.publish(Event.NEW_TOUR);
    }

    public List<Tour> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            criteria.from(Tour.class);

            return session.createQuery(criteria).getResultList();
        }
    }
}
