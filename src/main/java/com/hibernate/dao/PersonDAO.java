package com.hibernate.dao;

import com.hibernate.dto.PersonDTO;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class PersonDAO extends AbstractDAO implements ICRUD<PersonDTO, Long> {
   private static final Logger log = Logger.getLogger(PersonDAO.class);

   @Override
   public Long insertNonTransactional(PersonDTO value) {
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
   public Long insertTransactional(PersonDTO value) {
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
   public boolean updateNonTransactional(PersonDTO value) {
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
   public boolean updateTransactional(PersonDTO value) {
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
   public boolean deleteNonTransactional(PersonDTO value) {
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
   public boolean deleteTransactional(PersonDTO value) {
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
         PersonDTO personDTO = retrieve(value);
         session.delete(personDTO);
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
         beginSession();
         beginTransaction();
         PersonDTO personDTO = retrieve(value);
         session.delete(personDTO);
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
   public List<PersonDTO> retrieve() {
      List<PersonDTO> data;
      try {
         beginSession();
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<PersonDTO> criteria = builder.createQuery(PersonDTO.class);
         criteria.from(PersonDTO.class);
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
   public PersonDTO retrieve(Long id) {
      PersonDTO data;
      try {
         beginSession();
         data = session.get(PersonDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return data;
   }
}
