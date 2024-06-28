package org.example;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Animal {
    private final String species;
    private final String name;
    private final String breed;
    private final int age;

    @JsonCreator
    public Animal(@JsonProperty("species") String species,
                  @JsonProperty("name") String name,
                  @JsonProperty("breed") String breed,
                  @JsonProperty("age") int age) {
        this.species = species;
        this.name = name;
        this.breed = breed;
        this.age = age;
    }
}