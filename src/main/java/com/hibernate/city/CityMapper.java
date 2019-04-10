package com.hibernate.city;

import com.google.gson.JsonObject;
import com.hibernate.interfaces.IMapper;
import com.hibernate.state.State;
import com.hibernate.state.StateDAO;
import com.hibernate.state.StateDTO;
import com.hibernate.state.StateMapper;

public class CityMapper implements IMapper<City, CityDTO> {
   private StateMapper stateMapper = new StateMapper();

   @Override
   public CityDTO toDTO(City city) {
      StateDTO stateDTO = stateMapper.toDTO(city.getState());
      CityDTO cityDTO = new CityDTO();
      cityDTO.setCODE(city.getCode())
         .setName(city.getName())
         .setState(stateDTO);
      return cityDTO;
   }

   @Override
   public City toPOJO(CityDTO cityDTO) {
      State state = stateMapper.toPOJO(cityDTO.getState());
      City city = new City();
      city.setCode(cityDTO.getCODE())
         .setName(cityDTO.getName())
         .setState(state);
      return city;
   }

   @Override
   public CityDTO fromJson(JsonObject jsonObject) {
      CityDTO cityDTO = null;

      String code = (jsonObject.get("code").isJsonNull()) ? "" : jsonObject.get("code").getAsString();
      String name = (jsonObject.get("name").isJsonNull()) ? "" : jsonObject.get("name").getAsString();

      if (code.contains("-")) {
         String[] codeSplit = code.split("-");
         if (codeSplit.length == 3) {
            StateDTO stateDTO = new StateDAO().retrieve(code.substring(0, code.lastIndexOf("-")));
            cityDTO = new CityDTO();
            cityDTO.setCODE(code)
               .setName(name)
               .setState(stateDTO);
         }
      }

      return cityDTO;
   }
}
