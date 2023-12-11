package ru.apzakharov.projects.handlers.commands;

import jakarta.persistence.MappedSuperclass;
import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;

@MappedSuperclass
public interface Command {
    // Каким обработчиком будет пользоваться команда
    Class handler();
    // С помощью чего мы найдём эту команду
    Object getFindBy();
    // Ну и тут мы уже получим ответ на самом деле
    Answer getAnswer(ClassifiedUpdate update, User user);
}
