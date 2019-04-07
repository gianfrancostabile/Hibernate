package com.hibernate.abstractions;

import com.hibernate.util.HibernateUtil;
import org.hibernate.*;

public abstract class AbstractDAO {
   protected Session session = null;
   protected Transaction transaction = null;

   protected void beginSession() throws HibernateException {
      if (session == null) {
         session = HibernateUtil.getSessionFactory().openSession();
      }
   }

   protected void killSession() {
      if (session != null) {
         session.close();
         session = null;
      } else {
         throw new SessionException("Session was never open");
      }
   }

   protected void beginTransaction() throws HibernateException {
      if (session != null) {
         if (transaction == null) {
            transaction = session.beginTransaction();
         } else {
            throw new TransactionException("A transaction already exists");
         }
      } else {
         throw new SessionException("Session was never open");
      }
   }

   protected void commitTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.commit();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }

   protected void rollbackTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.rollback();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }
}
