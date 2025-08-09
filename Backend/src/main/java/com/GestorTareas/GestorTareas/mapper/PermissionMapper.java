package com.GestorTareas.GestorTareas.mapper;

import org.mapstruct.Mapper;

import com.GestorTareas.GestorTareas.dto.PermissionDTO;
import com.GestorTareas.GestorTareas.model.Permission;

@Mapper (componentModel = "spring")
public interface PermissionMapper {
    PermissionDTO tDto(Permission permission);
    Permission toEntity(PermissionDTO permission);
} 
