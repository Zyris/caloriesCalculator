package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.zyris.calorisecalculator.exception.IllegalArgumentException;
import com.zyris.calorisecalculator.exception.ProductNotFoundException;
import com.zyris.calorisecalculator.service.adapters.UpdateToMessageAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final UpdateMessageResolver updateOperationResolver;
    private final UpdateToMessageAdapter updateToMessageAdapter;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
                    updates.stream()
                            .peek(e -> log.debug("Received update: {}", e))
                            .map(updateToMessageAdapter::map)
                            .forEach(telegramMessage -> {
                                try {
                                    String message = updateOperationResolver.resolve(telegramMessage);
                                    telegramBot.execute(new SendMessage(telegramMessage.chatId(), message));
                                } catch (ProductNotFoundException | IllegalArgumentException ex) {
                                    telegramBot.execute(new SendMessage(telegramMessage.chatId(), ex.getMessage()));
                                }
                            });
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }
}
