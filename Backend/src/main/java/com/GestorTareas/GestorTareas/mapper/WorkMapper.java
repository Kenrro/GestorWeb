package com.GestorTareas.GestorTareas.mapper;

import org.mapstruct.Mapper;

import com.GestorTareas.GestorTareas.dto.WorkDTO;
import com.GestorTareas.GestorTareas.model.Work;

@Mapper(componentModel = "spring")
public interface WorkMapper {

    WorkDTO toDTO(Work work);
    Work toEntity(WorkDTO dto);

}
