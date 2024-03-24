package ru.fafurin.project;

import ru.fafurin.project.controllers.MainController;
import ru.fafurin.project.models.User;
import ru.fafurin.project.service.EntityService;
import ru.fafurin.project.service.LogService;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        User user = new User("Oleg", 32);
        Map<String, Object> data = new HashMap<>();

        MainController mainController = new MainController(new EntityService(), new LogService());
        mainController.saveUser(user, data);

    }
}
