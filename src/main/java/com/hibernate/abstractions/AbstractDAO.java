package com.hibernate.abstractions;

import com.hibernate.util.HibernateUtil;
import org.hibernate.*;

public abstract class AbstractDAO {
   protected Session session = null;
   protected Transaction transaction = null;
   private boolean manuallySession = false;

   public void beginSession() throws HibernateException {
      if (session == null) {
         session = HibernateUtil.getSessionFactory().openSession();
      }
   }

   public void killSession() {
      if (session != null) {
         if (!getManuallySession()) {
            session.close();
            session = null;
         }
      } else {
         throw new SessionException("Session was never open");
      }
   }

   public void beginTransaction() throws HibernateException {
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

   public void commitTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.commit();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }

   public void rollbackTransaction() throws HibernateException {
      if (transaction != null) {
         transaction.rollback();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }

   public void setManuallySession(boolean manuallySession) {
      this.manuallySession = manuallySession;
   }

   public boolean getManuallySession() {
      return manuallySession;
   }
}
