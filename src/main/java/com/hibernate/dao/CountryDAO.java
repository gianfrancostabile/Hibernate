package com.hibernate.dao;

import com.hibernate.dto.CountryDTO;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends AbstractDAO implements ICRUD<CountryDTO, Long> {
   private static final Logger log = Logger.getLogger(CountryDAO.class);

   @Override
   public Long insertNonTransactional(CountryDTO value) {
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
   public Long insertTransactional(CountryDTO value) {
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
   public boolean updateNonTransactional(CountryDTO value) {
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
   public boolean updateTransactional(CountryDTO value) {
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
   public boolean deleteNonTransactional(CountryDTO value) {
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
   public boolean deleteTransactional(CountryDTO value) {
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
         beginSession();
         CountryDTO countryDTO = retrieve(value);
         session.delete(countryDTO);
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
         CountryDTO countryDTO = retrieve(value);
         beginSession();
         beginTransaction();
         session.delete(countryDTO);
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
   public List<CountryDTO> retrieve() {
      List<CountryDTO> data;
      try {
         beginSession();
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<CountryDTO> criteria = builder.createQuery(CountryDTO.class);
         criteria.from(CountryDTO.class);
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
   public CountryDTO retrieve(Long id) {
      CountryDTO data;
      try {
         beginSession();
         data = session.get(CountryDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return data;
   }
}
