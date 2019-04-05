package com.hibernate.pojo;

public abstract class AbstractPOJO {
   public abstract int hashCode();
   public abstract String toString();
   public abstract boolean equals(Object o);
   public abstract AbstractPOJO clone();
}
