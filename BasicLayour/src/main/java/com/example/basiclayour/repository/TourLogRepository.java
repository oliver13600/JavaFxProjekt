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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.time.LocalDateTime;
import java.util.List;

public class TourLogRepository {

    private static final Logger logger = LogManager.getLogger(TourLogRepository.class);
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

        logger.info("New TourLog added");

        eventAggregator.publish(Event.NEW_LOG);
    }

    public List<TourLog> findAllLogsToTour(String selectedTour) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<TourLog> criteria = builder.createQuery(TourLog.class);
            Root<TourLog> root = criteria.from(TourLog.class);
            Join<TourLog, Tour> tourJoin = root.join("tour");
            criteria.select(root);
            criteria.where(builder.like(tourJoin.get("name"), "%" + selectedTour + "%"));

            logger.info("Logs from Tour: " + selectedTour + " added");

            return session.createQuery(criteria).getResultList();
        }
    }

    public void deleteTourLogByKeyword(LocalDateTime dateTime, String fromTour) {

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            Query<Tour> tourQuery = session.createQuery("FROM Tour WHERE name = :fromTour", Tour.class);
            tourQuery.setParameter("fromTour",  fromTour);
            Tour tour = tourQuery.uniqueResult();

            if (tour != null) {
                // Delete all associated tour logs
                List<TourLog> tourLogs = tour.getTourLogs();
                for (TourLog tourLog : tourLogs) {
                    if (tourLog.getDateTime().equals(dateTime)) {
                        tourLogs.remove(tourLog);
                        tour.setTourLogs(tourLogs); // Updates the tourLogs association in Tour entity with the modified list
                        session.saveOrUpdate(tour); // saves or updates the Tour entity to reflect the modified association
                        session.delete(tourLog);
                        break; // To only delete one TourLog
                    }
               }
            }
            transaction.commit();

            logger.info("Log with LocalDateTime:  " + dateTime + " deleted from Tour: " + fromTour);

            eventAggregator.publish(Event.DELETE_LOG);
        }
    }

}
