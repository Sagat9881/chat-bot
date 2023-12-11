package ru.apzakharov.projects;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotComponent extends TelegramLongPollingBot {
    // Создаём их объект для регистрации
    private final TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

    // Достаём токен бота
    @Value("${bot.token}")
    private String botToken;

    @PostConstruct
    private void init() throws TelegramApiException {
        telegramBotsApi.registerBot(this); // Регистрируем бота
    }

    public BotComponent() throws TelegramApiException {}

    @Override
    public void onUpdateReceived(Update update) {

    }

    @Override
    public String getBotUsername() {
        return null;
    }

}
