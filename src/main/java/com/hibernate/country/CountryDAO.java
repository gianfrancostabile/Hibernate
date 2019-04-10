package com.hibernate.country;

import com.hibernate.abstractions.AbstractDAO;
import com.hibernate.interfaces.ICRUD;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class CountryDAO extends AbstractDAO implements ICRUD<CountryDTO, String> {
   private static final Logger log = Logger.getLogger(CountryDAO.class);

   @Override
   public String insertNonTransactional(CountryDTO value) {
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
   public String insertTransactional(CountryDTO value) {
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
   public boolean updateNonTransactional(CountryDTO value) {
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
   public boolean deleteByIdNonTransactional(String value) {
      boolean deleted;
      try {
         CountryDTO countryDTO = retrieve(value);
         beginSession();
         session.delete(countryDTO);
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
      }
      return data;
   }

   @Override
   public CountryDTO retrieve(String id) {
      CountryDTO data;
      try {
         beginSession();
         data = session.find(CountryDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      }
      return data;
   }
}
