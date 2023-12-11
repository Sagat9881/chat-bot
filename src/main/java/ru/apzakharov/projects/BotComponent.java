package ru.apzakharov.projects;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.apzakharov.projects.dto.Answer;
import ru.apzakharov.projects.dto.ClassifiedUpdate;
import ru.apzakharov.projects.handlers.ClassifiedUpdateHandler;

@Component
public class BotComponent extends TelegramLongPollingBot {
    // Создаём их объект для регистрации
    private final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

    // Достаём токен бота
    @Value("${bot.token}")
    private String botToken;
    @Autowired
    private ClassifiedUpdateHandler handler;

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this); // Регистрируем бота
    }

    public BotComponent() throws TelegramApiException {
    }

    @Override
    public void onUpdateReceived(Update update) {
        try {
            execute(handler.request(new ClassifiedUpdate(update)).getBotApiMethod());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return null;
    }

}
