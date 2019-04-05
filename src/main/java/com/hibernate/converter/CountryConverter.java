package com.hibernate.converter;

import com.hibernate.dto.CountryDTO;
import com.hibernate.pojo.Country;

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
