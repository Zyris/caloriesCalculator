package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.exception.IllegalArgumentException;
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
            updates.stream()
                    .peek(System.out::println)
                    .map(TelegramMessage::from)
                    .forEach(telegramMessage -> {

                        try {
                            String message = updateOperationResolver.resolve(telegramMessage);
                            telegramBot.execute(new SendMessage(telegramMessage.getChatId(), message));
                        } catch (ProductNotFoundException | IllegalArgumentException ex) {
                            telegramBot.execute(new SendMessage(telegramMessage.getChatId(), ex.getMessage()));
                        }
                    });
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }
}
