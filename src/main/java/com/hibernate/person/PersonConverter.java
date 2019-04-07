package com.hibernate.person;

import com.hibernate.city.City;
import com.hibernate.city.CityConverter;
import com.hibernate.city.CityDTO;
import com.hibernate.interfaces.IConverter;

public class PersonConverter implements IConverter<Person, PersonDTO> {
   private CityConverter cityConverter = new CityConverter();

   @Override
   public PersonDTO toDTO(Person person) {
      CityDTO cityDTO = cityConverter.toDTO(person.getCity());
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
      City city = cityConverter.toPOJO(personDTO.getCity());
      Person person = new Person();
      person.setName(personDTO.getName())
         .setAge(personDTO.getAge())
         .setBirthday(personDTO.getBirthday())
         .setEmail(personDTO.getEmail())
         .setPhone(personDTO.getPhone())
         .setCity(city);
      return person;
   }
}
