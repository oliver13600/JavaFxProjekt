package com.example.basiclayour.data;

import com.example.basiclayour.model.Tour;
import com.example.basiclayour.model.TourLog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
public class HibernateSessionFactory {
    SessionFactory sessionFactory;

    public HibernateSessionFactory() {
        init();
    }

    private void init() {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure()
                        .build();

        MetadataSources sources = new MetadataSources(registry);
        sources.addAnnotatedClass(Tour.class);
        sources.addAnnotatedClass(TourLog.class);

        sessionFactory = sources.buildMetadata().buildSessionFactory();

        /*sessionFactory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();*/

    }

    public Session openSession() {
        return sessionFactory.openSession();
    }

}
