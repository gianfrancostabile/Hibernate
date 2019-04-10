package com.hibernate.state;

import com.google.gson.JsonObject;
import com.hibernate.country.Country;
import com.hibernate.country.CountryDAO;
import com.hibernate.country.CountryDTO;
import com.hibernate.country.CountryMapper;
import com.hibernate.interfaces.IMapper;

public class StateMapper implements IMapper<State, StateDTO> {
   private CountryMapper countryMapper = new CountryMapper();

   @Override
   public StateDTO toDTO(State state) {
      CountryDTO countryDTO = countryMapper.toDTO(state.getCountry());
      StateDTO stateDTO = new StateDTO();
      stateDTO.setCODE(state.getCode())
         .setName(state.getName())
         .setCountry(countryDTO);
      return stateDTO;
   }

   @Override
   public State toPOJO(StateDTO stateDTO) {
      Country country = countryMapper.toPOJO(stateDTO.getCountry());
      State state = new State();
      state.setCode(stateDTO.getCODE())
         .setName(stateDTO.getName())
         .setCountry(country);
      return state;
   }

   @Override
   public StateDTO fromJson(JsonObject jsonObject) {
      StateDTO stateDTO = null;

      String code = (jsonObject.get("code").isJsonNull()) ? "" : jsonObject.get("code").getAsString();
      String name = (jsonObject.get("name").isJsonNull()) ? "" : jsonObject.get("name").getAsString();

      if (code.contains("-")) {
         String[] codeSplit = code.split("-");
         if (codeSplit.length == 2) {
            CountryDTO countryDTO = new CountryDAO().retrieve(codeSplit[0]);

            stateDTO = new StateDTO();
            stateDTO.setCODE(code)
               .setName(name)
               .setCountry(countryDTO);
         }
      }
      return stateDTO;
   }
}
