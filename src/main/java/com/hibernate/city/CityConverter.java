package com.hibernate.city;

import com.hibernate.interfaces.IConverter;
import com.hibernate.state.State;
import com.hibernate.state.StateConverter;
import com.hibernate.state.StateDTO;

public class CityConverter implements IConverter<City, CityDTO> {
   private StateConverter stateConverter = new StateConverter();

   @Override
   public CityDTO toDTO(City city) {
      StateDTO stateDTO = stateConverter.toDTO(city.getState());
      CityDTO cityDTO = new CityDTO();
      cityDTO.setName(city.getName())
         .setState(stateDTO);
      return cityDTO;
   }

   @Override
   public City toPOJO(CityDTO cityDTO) {
      State state = stateConverter.toPOJO(cityDTO.getState());
      City city = new City();
      city.setName(cityDTO.getName())
         .setState(state);
      return city;
   }
}
