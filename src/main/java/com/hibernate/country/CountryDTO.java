package com.hibernate.country;

import java.io.Serializable;

public class CountryDTO implements Serializable {
   private String CODE;
   private String name;

   public CountryDTO() {

   }

   public String getCODE() {
      return CODE;
   }

   public CountryDTO setCODE(String code) {
      this.CODE = code;
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
