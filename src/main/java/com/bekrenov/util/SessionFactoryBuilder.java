package com.bekrenov.util;

import com.bekrenov.entity.Lend;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionFactoryBuilder {

    public static SessionFactory buildSessionFactory(){
        SessionFactory sessionFactory = new Configuration()
                                                .configure("hibernate.cfg.xml") // configuration file
                                                .addAnnotatedClass(Lend.class) // entity class
                                                .buildSessionFactory();
        return sessionFactory;
    }

}
