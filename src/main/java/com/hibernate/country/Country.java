package com.hibernate.country;

import com.hibernate.abstractions.AbstractPOJO;

import java.util.Objects;

public class Country extends AbstractPOJO {
   private String name;

   public Country() {
      this.setName("");
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   @Override
   public String toString() {
      return new StringBuilder().append("Country {")
         .append("name='").append(name).append("'")
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Country)) return false;
      Country country = (Country) o;
      return Objects.equals(getName(), country.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getName());
   }

   @Override
   public AbstractPOJO clone() {
      Country that = new Country();
      that.setName(getName());
      return that;
   }
}
