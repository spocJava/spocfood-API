package com.algaworks.algafood.core.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import java.io.IOException;

@JsonComponent
public class PageJacksonSerializer extends JsonSerializer<Page<?>> {

    @Override
    public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("content", value.getContent());
        gen.writeObjectField("size", value.getSize());
        gen.writeObjectField("totalElementos", value.getTotalElements());
        gen.writeObjectField("totalPages", value.getTotalPages());
        gen.writeObjectField("numberOfPage", value.getTotalPages());
        gen.writeEndObject();
    }
}
