package com.GestorTareas.GestorTareas.mapper;

import org.mapstruct.Mapper;

import com.GestorTareas.GestorTareas.dto.TasksGroupDTO;
import com.GestorTareas.GestorTareas.model.TasksGroup;

@Mapper(componentModel = "spring")
public interface TasksGroupMapper {
    TasksGroupDTO toDto (TasksGroup taskGroup);
    TasksGroup toEntity (TasksGroupDTO taskGroup);
}
