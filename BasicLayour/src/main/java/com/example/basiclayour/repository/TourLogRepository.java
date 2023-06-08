package com.example.basiclayour.repository;

import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;

import java.util.List;

public class TourLogRepository {
    private final HibernateSessionFactory sessionFactory;
    private final EventAggregator eventAggregator;

    public TourLogRepository(
            HibernateSessionFactory sessionFactory,
            EventAggregator eventAggregator
    ) {
        this.sessionFactory = sessionFactory;
        this.eventAggregator = eventAggregator;
    }

    public void save(TourLog tourLog) {
        try (Session session = sessionFactory.openSession()) {
            session.getTransaction().begin();
            session.persist(tourLog);
            session.getTransaction().commit();
        }

        eventAggregator.publish(Event.NEW_LOG);
    }

    public List<TourLog> findTourLogsByTour(String tourName) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TourLog> criteria = builder.createQuery(TourLog.class);
            Root<TourLog> root = criteria.from(TourLog.class);
            Join<TourLog, Tour> tourJoin = root.join("tour");
            criteria.select(root);
            criteria.where(builder.like(tourJoin.get("name"), "%" + tourName + "%"));

            return session.createQuery(criteria).getResultList();
        }
    }

    public List<TourLog> findAll() {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TourLog> criteria = builder.createQuery(TourLog.class);
            criteria.from(TourLog.class);

            return session.createQuery(criteria).getResultList();
        }
    }


}
