package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final UpdateMessageResolver updateOperationResolver;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            updates.forEach(update -> {
                System.out.println(update.message().text());
                String message = updateOperationResolver.resolve(update);
                long chatId = update.message().chat().id();
                telegramBot.execute(new SendMessage(chatId, message));
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
