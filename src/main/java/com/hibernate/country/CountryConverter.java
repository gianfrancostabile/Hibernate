package com.hibernate.country;

import com.hibernate.interfaces.IConverter;

public class CountryConverter implements IConverter<Country, CountryDTO> {
   @Override
   public CountryDTO toDTO(Country country) {
      CountryDTO countryDTO = new CountryDTO();
      countryDTO.setName(country.getName());
      return countryDTO;
   }

   @Override
   public Country toPOJO(CountryDTO countryDTO) {
      Country country = new Country();
      country.setName(countryDTO.getName());
      return country;
   }
}
