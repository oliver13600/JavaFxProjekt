package com.example.basiclayour.repository;


import com.example.basiclayour.event.Event;
import com.example.basiclayour.event.EventAggregator;

import java.io.IOException;
import java.util.List;

import com.example.basiclayour.data.HibernateSessionFactory;
import com.example.basiclayour.model.Tour;
import com.example.basiclayour.service.PropertiesFileService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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

    public List<Tour> findToursByKeyword(String keyword){
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Tour> criteria = builder.createQuery(Tour.class);
            Root<Tour> root = criteria.from(Tour.class);
            criteria.select(root);
            criteria.where(builder.like(root.get("name"), "%" + keyword + "%"));

            logger.info("Selected KeyWord for Searched Tours: " + keyword);

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

            Query<Tour> query = session.createQuery("DELETE FROM Tour WHERE name = :keyword");
            query.setParameter("keyword", keyword);
            query.executeUpdate();

            transaction.commit();

            logger.info("Tour deleted where name: " + keyword);
        }

        eventAggregator.publish(Event.DELETE_TOUR);
    }

}
