package com.hibernate.state;

import com.hibernate.abstractions.AbstractPOJO;
import com.hibernate.country.Country;

import java.util.Objects;

public class State extends AbstractPOJO {
   private String code;
   private String name;
   private Country country;

   public State() {

   }

   public String getCode() {
      return code;
   }

   public State setCode(String code) {
      this.code = code;
      return this;
   }

   public String getName() {
      return name;
   }

   public State setName(String name) {
      this.name = name;
      return this;
   }

   public Country getCountry() {
      return country;
   }

   public State setCountry(Country country) {
      this.country = country;
      return this;
   }

   @Override
   public String toString() {
      return new StringBuilder().append("State {")
         .append("code='").append(getCode()).append("'")
         .append(", name='").append(getName()).append("'")
         .append(", country=").append(getCountry())
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof State)) return false;
      State state = (State) o;
      return Objects.equals(getCode(), state.getCode()) &&
         Objects.equals(getName(), state.getName()) &&
         Objects.equals(getCountry(), state.getCountry());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getCode(), getName(), getCountry());
   }

   @Override
   public AbstractPOJO clone() {
      State that = new State();
      return that.setCode(getCode())
         .setName(getName())
         .setCountry(getCountry());
   }
}
