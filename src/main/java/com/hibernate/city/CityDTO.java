package com.hibernate.city;

import com.hibernate.state.StateDTO;

import java.io.Serializable;

public class CityDTO implements Serializable {
   private long ID;
   private String name;
   private StateDTO state;

   public CityDTO() {

   }

   public long getID() {
      return ID;
   }

   public CityDTO setID(long ID) {
      this.ID = ID;
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
