package com.GestorTareas.GestorTareas.mapper;

import org.mapstruct.Mapper;

import com.GestorTareas.GestorTareas.dto.UserDTO;
import com.GestorTareas.GestorTareas.model.User;


@Mapper(componentModel = "sprig")
public interface UserMapper {
    UserDTO toDto(User user);
    User toEntity(UserDTO user);
}
