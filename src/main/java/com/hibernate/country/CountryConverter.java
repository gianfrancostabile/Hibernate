package com.hibernate.country;

import com.hibernate.interfaces.IConverter;

public class CountryConverter implements IConverter<Country, CountryDTO> {
   @Override
   public CountryDTO toDTO(Country country) {
      CountryDTO countryDTO = new CountryDTO();
      countryDTO.setCODE(country.getCode())
         .setName(country.getName());
      return countryDTO;
   }

   @Override
   public Country toPOJO(CountryDTO countryDTO) {
      Country country = new Country();
      country.setCode(countryDTO.getCODE())
         .setName(countryDTO.getName());
      return country;
   }
}
