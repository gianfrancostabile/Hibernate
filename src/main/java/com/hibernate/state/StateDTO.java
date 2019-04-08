package com.hibernate.state;

import com.hibernate.country.CountryDTO;

import java.io.Serializable;

public class StateDTO implements Serializable {
   private String CODE;
   private String name;
   private CountryDTO country;

   public StateDTO() {

   }

   public String getCODE() {
      return CODE;
   }

   public StateDTO setCODE(String code) {
      this.CODE = code;
      return this;
   }

   public String getName() {
      return name;
   }

   public StateDTO setName(String name) {
      this.name = name;
      return this;
   }

   public CountryDTO getCountry() {
      return country;
   }

   public StateDTO setCountry(CountryDTO country) {
      this.country = country;
      return this;
   }
}
