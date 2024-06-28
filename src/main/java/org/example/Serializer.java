package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
public class Serializer {
    private final ObjectMapper mapper;

    public void serialize(File file, List<Animal> animal) {
        try {
            mapper.writeValue(file, animal);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public List<Animal> deserialize(File file) {
        try {
            if (file.exists() && file.length() > 0) {
                return mapper.readValue(file, new TypeReference<>() {
                });
            }
        } catch (IOException e) {
            System.err.println("Error loading data: " + e.getMessage());
        }

        return Collections.emptyList();
    }
}

