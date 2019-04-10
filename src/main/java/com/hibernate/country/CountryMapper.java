package com.hibernate.country;

import com.google.gson.JsonObject;
import com.hibernate.interfaces.IMapper;

public class CountryMapper implements IMapper<Country, CountryDTO> {
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

   @Override
   public CountryDTO fromJson(JsonObject jsonObject) {
      CountryDTO countryDTO = null;

      String code = (jsonObject.get("code").isJsonNull()) ? "" : jsonObject.get("code").getAsString();
      String name = (jsonObject.get("name").isJsonNull()) ? "" : jsonObject.get("name").getAsString();

      if (!code.contains("-")) {
         countryDTO = new CountryDTO();
         countryDTO.setCODE(code)
            .setName(name);
      }
      return countryDTO;
   }
}
