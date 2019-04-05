package com.hibernate.converter;

public interface IConverter<POJO, DTO> {
   DTO toDTO(POJO pojo);
   POJO toPOJO(DTO dto);
}
