package org.example;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Service {
    private List<Animal> animals;
    private Serializer serializer;
    private File file;

    public Service(Serializer animalSerializer, File file) {
        this.serializer = animalSerializer;
        this.file = file;
        this.animals = new ArrayList<>(animalSerializer.deserialize(file));
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean scRunning = true;

        while (scRunning) {
            System.out.println("""
                    -------------------
                    Select a menu item.
                    -------------------
                    Menu:
                    1. Add pet
                    2. Show all
                    3. Take pet from shelter
                    4. Exit
                    """);

            int answer = checkCorrectAge(sc);

            switch (answer) {
                case 1:
                    System.out.print("Enter the species of the animal: ");
                    String species = sc.nextLine();
                    System.out.print("Enter the name of the animal: ");
                    String name = sc.nextLine();
                    System.out.print("Enter the breed of the animal: ");
                    String breed = sc.nextLine();
                    System.out.print("Enter the age of the animal: ");
                    int age = checkCorrectAge(sc);
                    addPet(new Animal(species, name, breed, age));
                    break;
                case 2:
                    showAllPets();
                    break;
                case 3:
                    System.out.print("Enter the name of the animal to take from shelter: ");
                    String nameToRemove = sc.nextLine();
                    if (removePet(nameToRemove)) {
                        System.out.println("Animal " + nameToRemove + " was taken from the shelter.");
                    } else {
                        System.out.println("Animal with name " + nameToRemove + " not found in the shelter.");
                    }
                    break;
                case 4:
                    saveChanges();
                    scRunning = false;
                    break;
                default:
                    System.out.println("The selected menu item is missing");
            }
        }
        sc.close();
    }

    private int checkCorrectAge(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Incorrect entry - enter the age as an integer: ");
            }
        }
    }

    public boolean addPet(Animal animal) {
        return animals.add(animal);
    }

    public void showAllPets() {
        if (animals == null || animals.isEmpty()) {
            System.out.println("There are no animals in the shelter.");
        } else {
            System.out.println("List of animals in the shelter:");
            animals.forEach(System.out::println);
        }
    }

    public boolean removePet(String name) {
        return animals.removeIf(animal -> animal.getName().equalsIgnoreCase(name));
    }

    public void saveChanges() {
        serializer.serialize(file, animals);
    }
}
