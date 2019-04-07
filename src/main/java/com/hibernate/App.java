package com.hibernate;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Hello world!
 */
public class App {

   static Logger logger = Logger.getLogger(App.class);

   public static void main(String[] args) {
      BasicConfigurator.configure();
      // TODO : Make tests v1.0.5
   }

   @Deprecated
   public void testBefore_V104() {
      /*CountryDAO daoCountry = new CountryDAO();
      PersonDAO daoPerson = new PersonDAO();

      PersonConverter personConverter = new PersonConverter();
      CountryConverter countryConverter = new CountryConverter();

      Country country = new Country();
      CountryDTO countryDTO;
      Person person = new Person();
      PersonDTO personDTO;

      country.setName("Argentine");
      countryDTO = countryConverter.toDTO(country);

      Long idCountryInserted = daoCountry.insertTransactional(countryDTO);
      logger.info("Country inserted with ID - ".concat(idCountryInserted.toString()));
      countryDTO = daoCountry.retrieve(idCountryInserted);

      country = countryConverter.toPOJO(countryDTO);
      logger.info("Country Argentina retrieve: ".concat(country.toString()));

      person.setName("Gian Franco")
         .setAge(21)
         .setBirthday(new Date(890362800))
         .setEmail("gianfranco.stabile98@gmail.com")
         .setPhone("+542231111111")
         .setCountry(country);

      personDTO = personConverter.toDTO(person);
      personDTO.getCountry().setID(countryDTO.getID());

      Long idInserted = daoPerson.insertTransactional(personDTO);
      logger.info("Person inserted with ID - ".concat(idInserted.toString()));
      personDTO = daoPerson.retrieve(idInserted);

      Person personRetrieved = personConverter.toPOJO(personDTO);
      logger.info("Person I retrieved: ".concat(personRetrieved.toString()));

      countryDTO.setName("Brazil");
      daoCountry.updateTransactional(countryDTO);
      countryDTO = daoCountry.retrieve(countryDTO.getID());
      country = countryConverter.toPOJO(countryDTO);
      logger.info("Country Brazil retrieved: ".concat(country.toString()));

      personDTO.setName("Lionel Messi")
         .setAge(31)
         .setBirthday(new Date(551502000))
         .setEmail("lionelmessi@gmail.com")
         .setPhone("999999999");
      daoPerson.updateTransactional(personDTO);
      personDTO = daoPerson.retrieve(personDTO.getID());
      person = personConverter.toPOJO(personDTO);
      logger.info("Person Messi retrieved: ".concat(person.toString()));

      daoPerson.deleteByIdTransactional(personDTO.getID());
      logger.info("Messi was deleted.");
      daoCountry.deleteByIdTransactional(countryDTO.getID());
      logger.info("Brazil was deleted.");*/
   }
}
