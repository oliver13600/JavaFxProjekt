package com.example.basiclayour.repository;


import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

import java.util.List;
import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.model.Tour;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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

    public List<Tour> findToursByKeyword(String keyword){
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("tourDescription"), "%" + keyword + "%"));

            System.out.println("keywordTest: " + keyword );

            return session.createQuery(criteria).getResultList();
        }

    }

    public void searchTours(){
        eventAggregator.publish(Event.SEARCH_TOUR);
    }


    public void deleteTourByKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Tour> query = session.createQuery("DELETE FROM Tour WHERE tourDescription = :keyword");
            query.setParameter("keyword", keyword);
            query.executeUpdate();

            transaction.commit();
        }

        eventAggregator.publish(Event.DELETE_TOUR);
    }

    public void getSelectedItem(String selectedItem){
        eventAggregator.publish(Event.SELECTED_TOUR);
    }
}
