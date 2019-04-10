package com.hibernate.interfaces;

import com.google.gson.JsonObject;

public interface IMapper<POJO, DTO> {
   DTO toDTO(POJO pojo);

   POJO toPOJO(DTO dto);

   DTO fromJson(JsonObject jsonObject);
}
