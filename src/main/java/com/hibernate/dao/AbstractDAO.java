package com.hibernate.dao;

import com.hibernate.util.HibernateUtil;
import org.hibernate.*;

public abstract class AbstractDAO {
   protected Session session = null;
   protected Transaction transaction = null;

   protected void beginSession() throws HibernateException {
      if (session == null) {
         session = HibernateUtil.getSessionFactory().openSession();
      } else {
         throw new SessionException("Ya hay una sesión iniciada");
      }
   }

   protected void killSession() {
      if (session != null) {
         session.close();
         session = null;
      } else {
         throw new SessionException("No hay una sesión iniciada");
      }
   }

   protected void beginTransaction() throws HibernateException {
      if (session != null) {
         if (transaction == null) {
            transaction = session.beginTransaction();
         } else {
            throw new TransactionException("Ya hay una transacción iniciada");
         }
      } else {
         throw new SessionException("No hay una sesión iniciada");
      }
   }

   protected void commitTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.commit();
         transaction = null;
      } else {
         throw new TransactionException("No hay una transacción iniciada");
      }
   }

   protected void rollbackTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.rollback();
         transaction = null;
      } else {
         throw new TransactionException("No hay una transacción iniciada");
      }
   }
}
