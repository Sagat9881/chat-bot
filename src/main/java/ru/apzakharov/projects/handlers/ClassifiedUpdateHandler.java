package ru.apzakharov.projects.handlers;

import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.service.UserService;

public class ClassifiedUpdateHandler {

    private final UserService userService;

    private final HandlersMap commandMap;

    public ClassifiedUpdateHandler(UserService userService, HandlersMap commandMap) {
        this.userService = userService;
        this.commandMap = commandMap;
    }

    public Answer request(ClassifiedUpdate classifiedUpdate) {
        return commandMap.execute(classifiedUpdate,
                userService.findUserByUpdate(classifiedUpdate));
    }
}