package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ServiceTest {
    private Service service;
    private File testFile;

    @Before
    public void setUp() {
        testFile = new File("test_Service.json");
        ObjectMapper mapper = new ObjectMapper();
        Serializer serializer = new Serializer(mapper);
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("dog", "Scooby", "mastiff", 6));
        animals.add(new Animal("cat", "Abigel", "standard", 3));
        serializer.serialize(testFile, animals);
        service = new Service(serializer, testFile);
    }

    @Test
    public void testAddPet() {
        Animal newPet = new Animal("horse", "Polly", "arabic", 5);
        assertTrue(service.addPet(newPet));
        assertEquals(3, service.getAnimals().size());
    }

    @Test
    public void testRemovePet() {
        assertTrue(service.removePet("Scooby"));
        assertFalse(service.removePet("NonExistentPet"));
        assertEquals(1, service.getAnimals().size());
    }

    @Test
    public void testShowAllPets() {
        // Assuming animals are correctly loaded from the test file
        // You can add more specific assertions based on your actual implementation
        service.showAllPets();
    }

    @Test
    public void testSaveChanges() {
        Animal newPet = new Animal("horse", "Polly", "arabic", 5);
        service.addPet(newPet);
        service.saveChanges();
        ObjectMapper mapper = new ObjectMapper();
        List<Animal> updatedAnimals = new Serializer(mapper).deserialize(testFile);
        assertEquals(3, updatedAnimals.size());
    }
}
