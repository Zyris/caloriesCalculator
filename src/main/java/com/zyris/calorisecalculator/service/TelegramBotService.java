package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.zyris.calorisecalculator.exception.ProductNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final UpdateMessageResolver updateOperationResolver;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {

            System.out.println(updates);
            updates.forEach(update -> {
                long chatId = update.message().chat().id();
                try {
                    String message = updateOperationResolver.resolve(update);
                    telegramBot.execute(new SendMessage(chatId, message));
                } catch (ProductNotFoundException ex) {
                    telegramBot.execute(new SendMessage(chatId, ex.getMessage()));
                }
            });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
