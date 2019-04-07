package com.hibernate.city;

import com.hibernate.abstractions.AbstractDAO;
import com.hibernate.interfaces.ICRUD;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CityDAO extends AbstractDAO implements ICRUD<CityDTO, Long> {
   private static final Logger log = Logger.getLogger(CityDTO.class);

   @Override
   public Long insertNonTransactional(CityDTO value) {
      Long id;
      try {
         beginSession();
         id = (long) session.save(value);
      } catch (HibernateException he) {
         id = 0L;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return id;
   }

   @Override
   public Long insertTransactional(CityDTO value) {
      Long id;
      try {
         beginSession();
         beginTransaction();
         id = (Long) session.save(value);
         commitTransaction();
      } catch (HibernateException he) {
         id = 0L;
         log.error(he.getMessage(), he);
         rollbackTransaction();
      } finally {
         killSession();
      }
      return id;
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
      } finally {
         killSession();
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
      } finally {
         killSession();
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
   public boolean deleteByIdNonTransactional(Long value) {
      boolean deleted;
      try {
         CityDTO stateDTO = retrieve(value);
         beginSession();
         session.delete(stateDTO);
         deleted = true;
      } catch (HibernateException he) {
         deleted = false;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return deleted;
   }

   @Override
   public boolean deleteByIdTransactional(Long value) {
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
      } finally {
         killSession();
      }
      return data;
   }

   @Override
   public CityDTO retrieve(Long id) {
      CityDTO data;
      try {
         beginSession();
         data = session.get(CityDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return data;
   }
}
