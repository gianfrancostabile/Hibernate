package com.hibernate.city;

import com.hibernate.abstractions.AbstractDAO;
import com.hibernate.interfaces.ICRUD;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CityDAO extends AbstractDAO implements ICRUD<CityDTO, String> {
   private static final Logger log = Logger.getLogger(CityDTO.class);

   @Override
   public String insertNonTransactional(CityDTO value) {
      String code;
      try {
         beginSession();
         code = (String) session.save(value);
      } catch (HibernateException he) {
         code = "";
         log.error(he.getMessage(), he);
      }
      return code;
   }

   @Override
   public String insertTransactional(CityDTO value) {
      String code;
      try {
         beginSession();
         beginTransaction();
         code = (String) session.save(value);
         commitTransaction();
      } catch (HibernateException he) {
         code = "";
         log.error(he.getMessage(), he);
         rollbackTransaction();
      } finally {
         killSession();
      }
      return code;
   }

   @Override
   public boolean updateNonTransactional(CityDTO value) {
      boolean updated;
      try {
         beginSession();
         session.update(value);
         updated = true;
      } catch (HibernateException he) {
         updated = false;
         log.error(he.getMessage(), he);
      }
      return updated;
   }

   @Override
   public boolean updateTransactional(CityDTO value) {
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
   public boolean deleteNonTransactional(CityDTO value) {
      boolean deleted;
      try {
         beginSession();
         session.delete(value);
         deleted = true;
      } catch (HibernateException he) {
         deleted = false;
         log.error(he.getMessage(), he);
      }
      return deleted;
   }

   @Override
   public boolean deleteTransactional(CityDTO value) {
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
   public boolean deleteByIdNonTransactional(String value) {
      boolean deleted;
      try {
         CityDTO stateDTO = retrieve(value);
         beginSession();
         session.delete(stateDTO);
         deleted = true;
      } catch (HibernateException he) {
         deleted = false;
         log.error(he.getMessage(), he);
      }
      return deleted;
   }

   @Override
   public boolean deleteByIdTransactional(String value) {
      boolean deleted;
      try {
         CityDTO stateDTO = retrieve(value);
         beginSession();
         beginTransaction();
         session.delete(stateDTO);
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
   public List<CityDTO> retrieve() {
      List<CityDTO> data;
      try {
         beginSession();
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<CityDTO> criteria = builder.createQuery(CityDTO.class);
         criteria.from(CityDTO.class);
         data = session.createQuery(criteria).list();
      } catch (HibernateException he) {
         data = new ArrayList<>();
         log.error(he.getMessage(), he);
      }
      return data;
   }

   @Override
   public CityDTO retrieve(String id) {
      CityDTO data;
      try {
         beginSession();
         data = session.get(CityDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      }
      return data;
   }
}
