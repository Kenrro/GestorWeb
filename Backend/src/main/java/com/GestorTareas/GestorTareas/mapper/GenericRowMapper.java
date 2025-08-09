package com.GestorTareas.GestorTareas.mapper;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class GenericRowMapper<T> {

    private final Class<T> clazz;

    public GenericRowMapper(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T mapRow(ResultSet rs) {
        try {
            // Crear instancia vacía de la clase T
            T entity = clazz.getDeclaredConstructor().newInstance();

            // Obtener metadatos del ResultSet (nombres de columnas)
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String columnName = metaData.getColumnLabel(i); // Nombre de columna
                Object value = rs.getObject(i);                 // Valor de la columna

                // Buscar un campo en la clase con el mismo nombre que la columna
                try {
                    Field field = clazz.getDeclaredField(columnName);
                    field.setAccessible(true);

                    // Conversión automática de tipos
                    value = convertValue(value, field.getType());

                    field.set(entity, value);
                } catch (NoSuchFieldException e) {
                    // Si la clase no tiene un campo con ese nombre, se ignora
                }
            }
            return entity;
        } catch (Exception e) {
            throw new RuntimeException("Error mapping ResultSet to " + clazz.getSimpleName(), e);
        }
    }

    private Object convertValue(Object value, Class<?> targetType) {
        if (value == null) return null;

        if ((targetType == Boolean.class || targetType == boolean.class) && value instanceof Number) {
        return ((Number) value).intValue() != 0;
        }

        // Conversión de LocalDateTime a LocalDate
        if (value instanceof LocalDateTime && targetType == LocalDate.class) {
            return ((LocalDateTime) value).toLocalDate();
        }

        // Conversión de java.sql.Timestamp a LocalDate
        if (value instanceof Timestamp && targetType == LocalDate.class) {
            return ((Timestamp) value).toLocalDateTime().toLocalDate();
        }

        // Conversión de java.sql.Date a LocalDate
        if (value instanceof java.sql.Date && targetType == LocalDate.class) {
            return ((java.sql.Date) value).toLocalDate();
        }

        return value;
    }
}