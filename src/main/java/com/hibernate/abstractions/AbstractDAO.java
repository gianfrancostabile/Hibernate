package com.hibernate.abstractions;

import com.hibernate.util.HibernateUtil;
import org.hibernate.*;

import javax.persistence.PersistenceException;
import javax.persistence.RollbackException;

public abstract class AbstractDAO {
   protected Session session = null;
   protected Transaction transaction = null;

   /**
    * Open a session from SessionFactory. <br>
    * If a session was open, but never close, session attribute is not override.
    *
    * @throws HibernateException indicates a problem opening the session; pretty rare here.
    * @see SessionFactory#openSession()
    */
   public void beginSession() throws HibernateException {
      if (session == null) {
         session = HibernateUtil.getSessionFactory().openSession();
      }
   }

   /**
    * Destroy an existing session.
    *
    * @throws SessionException if the session does not exists.
    * @see Session#close()
    */
   public void killSession() throws SessionException {
      if (session != null) {
         session.close();
         session = null;
      } else {
         throw new SessionException("Session was never open");
      }
   }

   /**
    * Open a new transaction.
    *
    * @throws TransactionException if the session have never been open.
    * @throws SessionException     if the transaction is already opened.
    * @see Session#beginTransaction()
    */
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

   /**
    * Commit an opened transaction
    *
    * @throws TransactionException if the transaction have never been open or the transaction is INACTIVE.
    * @throws RollbackException    if the commit fails.
    * @see Transaction#commit()
    */
   public void commitTransaction() throws TransactionException, RollbackException {
      if (transaction != null && transaction.isActive()) {
         session.flush();
         transaction.commit();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }

   /**
    * Rollback an opened transaction
    *
    * @throws TransactionException if transaction have never been open or the transaction is INACTIVE.
    * @throws RollbackException    if an unexpected error condition is encountered.
    * @see Transaction#rollback()
    */
   public void rollbackTransaction() throws PersistenceException {
      if (transaction != null && transaction.isActive()) {
         session.flush();
         transaction.rollback();
         transaction = null;
      } else {
         throw new TransactionException("A transaction was never begun");
      }
   }
}
