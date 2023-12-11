package ru.apzakharov.projects.handlers;

import jakarta.persistence.MappedSuperclass;
import ru.apzakharov.projects.domain.User;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.dto.TelegramType;

@MappedSuperclass
public interface Handler {
    // Какой тип сообщения будет обработан
    TelegramType getHandleType();
    // Приоритет обработчика
    int priority();
    // Условия, при которых мы воспользуемся этим обработчиком
    boolean condition(User user, ClassifiedUpdate update);
    // В этом методе, с помощью апдейта мы будем получать answer
    Answer getAnswer(User user, ClassifiedUpdate update);
}
