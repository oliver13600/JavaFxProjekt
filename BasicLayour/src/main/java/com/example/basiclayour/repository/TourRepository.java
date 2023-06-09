package com.example.basiclayour.repository;


import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

import java.io.IOException;
import java.util.List;

import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import com.example.basiclayour.service.PropertiesFileService;
import jakarta.persistence.criteria.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class TourRepository {

    private final HibernateSessionFactory sessionFactory;
    private static final Logger logger = LogManager.getLogger(TourRepository.class);
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

        logger.info("New Tour created" + tour.getName());

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

    public List<Tour> findToursByKeyword(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(builder.lower(root.get("tourInformation")), "%" + keyword.toLowerCase() + "%"));

            logger.info("Selected Keyword for Searched Tours: " + keyword);

            return session.createQuery(criteria).getResultList();
        }
    }



    public Tour getTour(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("name"), "%" + keyword + "%"));

            logger.info("Selected Tour for TourDescription: " + keyword);

            Query<Tour> query = session.createQuery(criteria);
            return query.setMaxResults(1).uniqueResult();
        }
    }

    public Tour findTourByName(String keyword) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("name"), "%" + keyword + "%"));

            logger.info("Find singular Tour by name for Pdf-File: " + keyword);

            return session.createQuery(criteria)
                    .setMaxResults(1)
                    .uniqueResult();
        }
    }

    public List<Tour> findToursByName(String keyword){
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("name"), "%" + keyword + "%"));

            logger.info("Find multiple Tours by name: " + keyword);

            return session.createQuery(criteria).getResultList();
        }
    }

    public void searchTours(){
        eventAggregator.publish(Event.SEARCH_TOUR);
    }



    public void deleteTourByKeyword(String keyword) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Tour> tourQuery = session.createQuery("FROM Tour WHERE name = :keyword", Tour.class);
            tourQuery.setParameter("keyword",  keyword);
            Tour tour = tourQuery.uniqueResult();

            if (tour != null) {
                // Delete all associated tour logs
                List<TourLog> tourLogs = tour.getTourLogs();
                for (TourLog tourLog : tourLogs) {
                    session.delete(tourLog);
                }

                // Clear the association between tour and tour logs
                tourLogs.clear();

                // Delete the tour itself
                session.delete(tour);
            }
            transaction.commit();

            logger.info("Tour:  " + keyword + " + all of its TourLogs deleted.");

            eventAggregator.publish(Event.DELETE_TOUR);
        }
    }

    public int getMatchesInTourDescription(String searchTerm){
        try (Session session = sessionFactory.openSession()) {
            Query<Long> countQuery = session.createQuery("SELECT COUNT(*) FROM Tour WHERE tourDescription LIKE :searchTerm");
            countQuery.setParameter("searchTerm", "%" + searchTerm + "%");
            Long count = countQuery.uniqueResult();

            logger.info("TourDescription: "+ searchTerm + "found " + count + " matches in the database");

            return count.intValue();
        }
    }

}
