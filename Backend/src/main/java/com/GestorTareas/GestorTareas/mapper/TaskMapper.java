package com.GestorTareas.GestorTareas.mapper;

import org.mapstruct.Mapper;

import com.GestorTareas.GestorTareas.dto.TaskDTO;
import com.GestorTareas.GestorTareas.model.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {
    TaskDTO toDto(Task task);
    Task toEntity(TaskDTO taskDto);
}
