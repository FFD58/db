package ru.fafurin.project.controllers;

import ru.fafurin.project.models.User;
import ru.fafurin.project.service.EntityService;
import ru.fafurin.project.service.LogService;

import java.io.Closeable;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class MainController implements Closeable {
    private final EntityService entityService;
    private final LogService logService;

    public MainController(EntityService entityService, LogService logService) {
        this.entityService = entityService;
        this.logService = logService;
    }

    public void saveUser(User user, Map<String, Object> meta) {
        entityService.save(user);
        meta.put("method", "save_user");
        meta.put("user_id", user.getId());
        meta.put("date_time", LocalDateTime.now());
        logService.add(meta);
        logService.printLogs();
    }

    @Override
    public void close() throws IOException {
        entityService.close();
        logService.close();
    }
}
