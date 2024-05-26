package org.example;

import com.fasterxml.jackson.databind.json.JsonMapper;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Service service = new Service(
                new Serializer(new JsonMapper()),
                new File("src/main/resources/animals.json")
        );

        service.run();
    }
}

