package com.mahorad;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonDeserialize(using = CurrencyDeserializer.class)
public class Veggie {

    @Id
    @NotNull
    private final String id;

    @NotNull
    @Size(max = 45)
    private final String name;

    @NotNull
    @JsonProperty("price")
    @JsonSerialize(using = CurrencySerializer.class)
    private final BigDecimal fee;

    public Veggie(String id, String name, BigDecimal fee) {
        this.id = id;
        this.name = name;
        this.fee = fee;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getFee() {
        return fee;
    }
}