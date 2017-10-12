package com.mahorad.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.mahorad.model.Veggie;

import java.io.IOException;
import java.math.BigDecimal;

public class CurrencyDeserializer extends JsonDeserializer<Veggie> {
    @Override
    public Veggie deserialize(
                JsonParser jsonParser,
                DeserializationContext deserializationContext)
            throws IOException {

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        final String id = node.get("id").asText();
        final String name = node.get("name").asText();
        final String price = node.get("price").asText();
        final BigDecimal fee = new BigDecimal(price);
        return new Veggie(id, name, fee);
    }
}
