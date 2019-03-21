package com.test.dao;

import com.test.pojo.Contacto;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import java.util.ArrayList;
import java.util.List;

public class ContactoDAO extends DAO implements CRUD<Contacto> {
   private static final String tableName = "CONTACTOS";
   private static final Logger log = Logger.getLogger(ContactoDAO.class);

   @Override
   public long create(Contacto value) throws HibernateException {
      long id;
      try {
         beginSession();
         beginTransaction();
         id = (long) session.save(value);
         commitTransaction();
      } catch (HibernateException he) {
         id = 0;
         log.error(he.getMessage(), he);
         rollbackTransaction();
      } finally {
         killSession();
      }
      return id;
   }

   @Override
   public boolean update(Contacto value) {
      boolean updated;
      try {
         beginSession();
         beginTransaction();
         session.update(value);
         commitTransaction();
         updated = true;
      } catch (HibernateException he) {
         updated = false;
         log.error(he.getMessage(), he);
         rollbackTransaction();
      } finally {
         killSession();
      }
      return updated;
   }

   @Override
   public boolean delete(Contacto value) {
      boolean deleted;
      try {
         beginSession();
         beginTransaction();
         session.delete(value);
         commitTransaction();
         deleted = true;
      } catch (HibernateException he) {
         deleted = false;
         log.error(he.getMessage(), he);
         rollbackTransaction();
      } finally {
         killSession();
      }
      return deleted;
   }

   @Override
   public List<Contacto> retrieve() {
      List<Contacto> contactos;
      try {
         beginSession();
         beginTransaction();
         contactos = session.createQuery(new StringBuffer().append("FROM").append(tableName).toString()
            , Contacto.class).list();
      } catch (HibernateException he) {
         contactos = new ArrayList<>();
         log.error(he.getMessage(), he);
      } finally {
         commitTransaction();
         killSession();
      }
      return contactos;
   }

   @Override
   public Contacto retrieve(long id) {
      Contacto contacto;
      try {
         beginSession();
         beginTransaction();
         contacto = session.load(Contacto.class, id);
      } catch (HibernateException he) {
         contacto = null;
         log.error(he.getMessage(), he);
      } finally {
         commitTransaction();
         killSession();
      }
      return contacto;
   }
}
