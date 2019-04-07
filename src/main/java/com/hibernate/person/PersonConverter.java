package com.hibernate.person;

import com.hibernate.country.Country;
import com.hibernate.country.CountryConverter;
import com.hibernate.country.CountryDTO;
import com.hibernate.interfaces.IConverter;

public class PersonConverter implements IConverter<Person, PersonDTO> {
   private CountryConverter countryConverter = new CountryConverter();

   @Override
   public PersonDTO toDTO(Person person) {
      CountryDTO countryDTO = countryConverter.toDTO(person.getCountry());
      PersonDTO personDTO = new PersonDTO();
      personDTO.setName(person.getName())
         .setAge(person.getAge())
         .setBirthday(person.getBirthday())
         .setEmail(person.getEmail())
         .setPhone(person.getPhone())
         .setCountry(countryDTO);
      return personDTO;
   }

   @Override
   public Person toPOJO(PersonDTO personDTO) {
      Country country = countryConverter.toPOJO(personDTO.getCountry());
      Person person = new Person();
      person.setName(personDTO.getName())
         .setAge(personDTO.getAge())
         .setBirthday(personDTO.getBirthday())
         .setEmail(personDTO.getEmail())
         .setPhone(personDTO.getPhone())
         .setCountry(country);
      return person;
   }
}
