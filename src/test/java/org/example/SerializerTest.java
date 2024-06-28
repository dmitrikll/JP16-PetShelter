package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SerializerTest {
    private Serializer serializer;
    private File testFile;

    @Before
    public void setUp() {
        ObjectMapper mapper = new ObjectMapper();
        serializer = new Serializer(mapper);
        testFile = new File("test_Serializer.json");
    }

    @Test
    public void testSerializeAndDeserialize() throws IOException {
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", "Scooby", "mastiff", 6));
        animals.add(new Animal("cat", "Abigel", "standard", 3));

        // Serialize
        serializer.serialize(testFile, animals);

        // Deserialize
        List<Animal> deserializedAnimals = serializer.deserialize(testFile);

        assertEquals(animals.size(), deserializedAnimals.size());
        assertEquals(animals.get(0).getSpecies(), deserializedAnimals.get(0).getSpecies());
        assertEquals(animals.get(0).getName(), deserializedAnimals.get(0).getName());
        assertEquals(animals.get(0).getBreed(), deserializedAnimals.get(0).getBreed());
        assertEquals(animals.get(0).getAge(), deserializedAnimals.get(0).getAge());
        assertEquals(animals.get(1).getSpecies(), deserializedAnimals.get(1).getSpecies());
        assertEquals(animals.get(1).getName(), deserializedAnimals.get(1).getName());
        assertEquals(animals.get(1).getBreed(), deserializedAnimals.get(1).getBreed());
        assertEquals(animals.get(1).getAge(), deserializedAnimals.get(1).getAge());
    }
}
