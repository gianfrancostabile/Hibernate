package com.hibernate.state;

import com.hibernate.abstractions.AbstractDAO;
import com.hibernate.interfaces.ICRUD;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;

public class StateDAO extends AbstractDAO implements ICRUD<StateDTO, String> {
   private static final Logger log = Logger.getLogger(StateDAO.class);

   @Override
   public String insertNonTransactional(StateDTO value) {
      String code;
      try {
         beginSession();
         code = (String) session.save(value);
      } catch (HibernateException he) {
         code = "";
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return code;
   }

   @Override
   public String insertTransactional(StateDTO value) {
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
   public boolean updateNonTransactional(StateDTO value) {
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
   public boolean updateTransactional(StateDTO value) {
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
   public boolean deleteNonTransactional(StateDTO value) {
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
   public boolean deleteTransactional(StateDTO value) {
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
         StateDTO stateDTO = retrieve(value);
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
   public boolean deleteByIdTransactional(String value) {
      boolean deleted;
      try {
         StateDTO stateDTO = retrieve(value);
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
   public List<StateDTO> retrieve() {
      List<StateDTO> data;
      try {
         beginSession();
         CriteriaBuilder builder = session.getCriteriaBuilder();
         CriteriaQuery<StateDTO> criteria = builder.createQuery(StateDTO.class);
         criteria.from(StateDTO.class);
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
   public StateDTO retrieve(String id) {
      StateDTO data;
      try {
         beginSession();
         data = session.get(StateDTO.class, id);
      } catch (HibernateException he) {
         data = null;
         log.error(he.getMessage(), he);
      } finally {
         killSession();
      }
      return data;
   }
}
