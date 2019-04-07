package com.hibernate.person;

import com.hibernate.city.CityDTO;

import java.io.Serializable;
import java.sql.Date;

public class PersonDTO implements Serializable {
   private long ID;
   private String name;
   private int age;
   private Date birthday;
   private String email;
   private String phone;
   private CityDTO city;

   public PersonDTO() {

   }

   public long getID() {
      return ID;
   }

   public PersonDTO setID(long id) {
      this.ID = id;
      return this;
   }

   public String getName() {
      return name;
   }

   public PersonDTO setName(String name) {
      this.name = name;
      return this;
   }

   public int getAge() {
      return age;
   }

   public PersonDTO setAge(int age) {
      this.age = age;
      return this;
   }

   public Date getBirthday() {
      return birthday;
   }

   public PersonDTO setBirthday(Date birthday) {
      this.birthday = birthday;
      return this;
   }

   public String getEmail() {
      return email;
   }

   public PersonDTO setEmail(String email) {
      this.email = email;
      return this;
   }

   public String getPhone() {
      return phone;
   }

   public PersonDTO setPhone(String phone) {
      this.phone = phone;
      return this;
   }

   public CityDTO getCity() {
      return city;
   }

   public PersonDTO setCity(CityDTO city) {
      this.city = city;
      return this;
   }
}
