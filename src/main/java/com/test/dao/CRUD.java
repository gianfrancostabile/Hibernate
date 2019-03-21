package com.test.dao;

import java.util.List;

public interface CRUD<T> {
   long create(T value);

   boolean update(T value);

   boolean delete(T value);

   List<T> retrieve();

   T retrieve(long id);
}
