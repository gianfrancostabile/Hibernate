package com.hibernate;

import com.hibernate.converter.CountryConverter;
import com.hibernate.converter.PersonConverter;
import com.hibernate.dao.CountryDAO;
import com.hibernate.dao.PersonDAO;
import com.hibernate.dto.CountryDTO;
import com.hibernate.dto.PersonDTO;
import com.hibernate.pojo.Country;
import com.hibernate.pojo.Person;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.sql.Date;

/**
 * Hello world!
 */
public class App {

   static Logger logger = Logger.getLogger(App.class);

   public static void main(String[] args) {
      BasicConfigurator.configure();

      PersonConverter personConverter = new PersonConverter();
      CountryConverter countryConverter = new CountryConverter();

      Country country = new Country();
      country.setName("Argentina");

      CountryDTO countryDTO = countryConverter.toDTO(country);

      CountryDAO daoCountry = new CountryDAO();
      Long idCountryInserted = daoCountry.insertTransactional(countryDTO);
      logger.info("Country inserted with ID - ".concat(idCountryInserted.toString()));
      CountryDTO countryDTORetrieved = daoCountry.retrieve(idCountryInserted);

      Country countryRetrieved = countryConverter.toPOJO(countryDTORetrieved);
      logger.info(countryRetrieved.toString());


      Person person = new Person();
      person.setName("Gian Franco")
         .setAge(21)
         .setBirthday(new Date(890352000000L))
         .setEmail("gianfranco.stabile98@gmail.com")
         .setPhone("+542231111111")
         .setCountry(countryRetrieved);

      PersonDTO personDTO = personConverter.toDTO(person);
      personDTO.getCountry().setID(countryDTORetrieved.getID());

      PersonDAO daoPerson = new PersonDAO();
      Long idInserted = daoPerson.insertTransactional(personDTO);
      logger.info("Person inserted with ID - ".concat(idInserted.toString()));
      PersonDTO personDTORetrieved = daoPerson.retrieve(idInserted);

      Person personRetrieved = personConverter.toPOJO(personDTORetrieved);
      logger.info(personRetrieved.toString());

   }
}
