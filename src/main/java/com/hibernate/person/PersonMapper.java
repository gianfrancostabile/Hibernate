package com.hibernate.person;

import com.google.gson.JsonObject;
import com.hibernate.city.City;
import com.hibernate.city.CityDAO;
import com.hibernate.city.CityDTO;
import com.hibernate.city.CityMapper;
import com.hibernate.interfaces.IMapper;

import java.sql.Date;

public class PersonMapper implements IMapper<Person, PersonDTO> {
   private CityMapper cityMapper = new CityMapper();

   @Override
   public PersonDTO toDTO(Person person) {
      CityDTO cityDTO = cityMapper.toDTO(person.getCity());
      PersonDTO personDTO = new PersonDTO();
      personDTO.setName(person.getName())
         .setAge(person.getAge())
         .setBirthday(person.getBirthday())
         .setEmail(person.getEmail())
         .setPhone(person.getPhone())
         .setCity(cityDTO);
      return personDTO;
   }

   @Override
   public Person toPOJO(PersonDTO personDTO) {
      City city = cityMapper.toPOJO(personDTO.getCity());
      Person person = new Person();
      person.setName(personDTO.getName())
         .setAge(personDTO.getAge())
         .setBirthday(personDTO.getBirthday())
         .setEmail(personDTO.getEmail())
         .setPhone(personDTO.getPhone())
         .setCity(city);
      return person;
   }

   @Override
   public PersonDTO fromJson(JsonObject jsonObject) {
      PersonDTO personDTO = new PersonDTO();

      long id = (jsonObject.get("id").isJsonNull()) ? 0L : jsonObject.get("id").getAsLong();
      String name = (jsonObject.get("name").isJsonNull()) ? "" : jsonObject.get("name").getAsString();
      int age = (jsonObject.get("age").isJsonNull()) ? 0 : jsonObject.get("age").getAsInt();
      Date birthday = new Date((jsonObject.get("birthday").isJsonNull()) ? 0L : jsonObject.get("birthday").getAsLong());
      String email = (jsonObject.get("email").isJsonNull()) ? "" : jsonObject.get("email").getAsString();
      String phone = (jsonObject.get("phone").isJsonNull()) ? "" : jsonObject.get("phone").getAsString();
      String cityCode = (jsonObject.get("city").isJsonNull()) ? "" : jsonObject.get("city").getAsString();
      CityDTO cityDTO = new CityDAO().retrieve(cityCode);

      personDTO.setID(id)
         .setName(name).setAge(age)
         .setBirthday(birthday).setEmail(email)
         .setPhone(phone).setCity(cityDTO);
      return personDTO;
   }
}
