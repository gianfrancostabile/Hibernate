package com.test.utilities;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
   private static final Logger log = Logger.getLogger(HibernateUtil.class);
   private static final SessionFactory sessionFactory;

   static {
      try {
         sessionFactory = new Configuration().configure().buildSessionFactory();
      } catch (HibernateException he) {
         log.fatal(he.getMessage(), he);
         throw new ExceptionInInitializerError(he);
      }
   }

   public static SessionFactory getSessionFactory() {
      return sessionFactory;
   }
}
