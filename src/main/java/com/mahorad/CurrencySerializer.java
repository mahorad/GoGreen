package com.mahorad;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class CurrencySerializer extends JsonSerializer<BigDecimal> {
    @Override
    public void serialize(
                BigDecimal value,
                JsonGenerator generator,
                SerializerProvider provider)
            throws IOException, JsonProcessingException {

        final BigDecimal bigDecimal = value.setScale(2, RoundingMode.HALF_UP);
        generator.writeString(bigDecimal.toString());
    }
}