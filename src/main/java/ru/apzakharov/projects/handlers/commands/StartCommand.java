package ru.apzakharov.projects.handlers.commands;

import lombok.SneakyThrows;
import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.handlers.CommandHandler;
import ru.apzakharov.projects.util.SendMessageBuilder;

public class StartCommand implements Command {
    @Override
    public Class handler() {
        return CommandHandler.class;
    }

    @Override
    public Object getFindBy() {
        return "/start";
    }

    @SneakyThrows
    @Override
    public Answer getAnswer(ClassifiedUpdate update, User user) {
        return new SendMessageBuilder().chatId(user.getChatId()).message("Hello!").build();
    }
}
