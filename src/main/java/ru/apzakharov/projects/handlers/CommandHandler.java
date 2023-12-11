package ru.apzakharov.projects.handlers;

import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.dto.TelegramType;
import ru.apzakharov.projects.handlers.commands.Command;

import java.util.HashMap;

public class CommandHandler extends AbstractHandler {

    private HashMap<Object, Command> hashMap = new HashMap<>();

    @Override
    protected HashMap<Object, Command> createMap() {
        return hashMap;
    }

    @Override
    public TelegramType getHandleType() {
        return TelegramType.Command;
    }

    @Override
    public int priority() {
        return 1;
    }

    @Override
    public boolean condition(User user, ClassifiedUpdate update) {
        return hashMap.containsKey(update.getCommandName());
    }

    @Override
    public Answer getAnswer(User user, ClassifiedUpdate update) {
        return hashMap.get(update.getCommandName()).getAnswer(update, user);
    }
}