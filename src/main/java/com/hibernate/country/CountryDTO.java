package com.hibernate.country;

import java.io.Serializable;

public class CountryDTO implements Serializable {
   private long ID;
   private String name;

   public CountryDTO() {

   }

   public long getID() {
      return ID;
   }

   public CountryDTO setID(long id) {
      this.ID = id;
      return this;
   }

   public String getName() {
      return name;
   }

   public CountryDTO setName(String name) {
      this.name = name;
      return this;
   }
}
