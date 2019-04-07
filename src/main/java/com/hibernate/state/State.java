package com.hibernate.state;

import com.hibernate.abstractions.AbstractPOJO;
import com.hibernate.country.Country;

import java.util.Objects;

public class State extends AbstractPOJO {
   private String name;
   private Country country;

   public State() {

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
         .append("name='").append(name).append("'")
         .append(", country=").append(country)
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof State)) return false;
      State state = (State) o;
      return Objects.equals(getName(), state.getName()) &&
         Objects.equals(getCountry(), state.getCountry());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName(), getCountry());
   }

   @Override
   public AbstractPOJO clone() {
      State that = new State();
      return that.setName(getName())
         .setCountry(getCountry());
   }
}
