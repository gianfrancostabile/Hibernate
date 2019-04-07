package com.hibernate.interfaces;

import java.util.List;

public interface ICRUD<T, K> {
   K insertNonTransactional(T value);

   K insertTransactional(T value);

   boolean updateNonTransactional(T value);

   boolean updateTransactional(T value);

   boolean deleteNonTransactional(T value);

   boolean deleteTransactional(T value);

   boolean deleteByIdNonTransactional(K value);

   boolean deleteByIdTransactional(K value);

   List<T> retrieve();

   T retrieve(K id);
}
