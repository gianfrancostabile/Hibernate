package com.hibernate.city;

import com.hibernate.state.StateDTO;

import java.io.Serializable;

public class CityDTO implements Serializable {
   private String CODE;
   private String name;
   private StateDTO state;

   public CityDTO() {

   }

   public String getCODE() {
      return CODE;
   }

   public CityDTO setCODE(String code) {
      this.CODE = code;
      return this;
   }

   public String getName() {
      return name;
   }

   public CityDTO setName(String name) {
      this.name = name;
      return this;
   }

   public StateDTO getState() {
      return state;
   }

   public CityDTO setState(StateDTO state) {
      this.state = state;
      return this;
   }
}
