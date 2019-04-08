package com.hibernate.country;

import com.hibernate.abstractions.AbstractPOJO;

import java.util.Objects;

public class Country extends AbstractPOJO {
   private String code;
   private String name;

   public Country() {

   }

   public String getCode() {
      return code;
   }

   public Country setCode(String code) {
      this.code = code;
      return this;
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
         .append("code='").append(getCode()).append("'")
         .append(", name='").append(getName()).append("'")
         .append("}").toString();
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof Country)) return false;
      Country country = (Country) o;
      return Objects.equals(getCode(), country.getCode()) &&
         Objects.equals(getName(), country.getName());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getCode(), getName());
   }

   @Override
   public AbstractPOJO clone() {
      Country that = new Country();
      that.setCode(getCode())
         .setName(getName());
      return that;
   }
}
