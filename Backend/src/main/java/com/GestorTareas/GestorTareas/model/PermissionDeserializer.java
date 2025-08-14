package com.GestorTareas.GestorTareas.model;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class PermissionDeserializer extends StdDeserializer<Permission> {

    public PermissionDeserializer() {
        super(Permission.class);
    }

    @Override
    public Permission deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        JsonNode node = jp.getCodec().readTree(jp);
        int id = node.get("id").asInt();
        return Permission.fromId(id);
    }
}
