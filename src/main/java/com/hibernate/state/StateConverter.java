package com.hibernate.state;

import com.hibernate.country.Country;
import com.hibernate.country.CountryConverter;
import com.hibernate.country.CountryDTO;
import com.hibernate.interfaces.IConverter;

public class StateConverter implements IConverter<State, StateDTO> {
   private CountryConverter countryConverter = new CountryConverter();

   @Override
   public StateDTO toDTO(State state) {
      CountryDTO countryDTO = countryConverter.toDTO(state.getCountry());
      StateDTO stateDTO = new StateDTO();
      stateDTO.setCODE(state.getCode())
         .setName(state.getName())
         .setCountry(countryDTO);
      return stateDTO;
   }

   @Override
   public State toPOJO(StateDTO stateDTO) {
      Country country = countryConverter.toPOJO(stateDTO.getCountry());
      State state = new State();
      state.setCode(stateDTO.getCODE())
         .setName(stateDTO.getName())
         .setCountry(country);
      return state;
   }
}
