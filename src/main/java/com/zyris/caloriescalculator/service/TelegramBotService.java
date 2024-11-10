package com.zyris.caloriescalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.zyris.caloriescalculator.domain.ResponseContext;
import com.zyris.caloriescalculator.exception.IllegalArgumentException;
import com.zyris.caloriescalculator.exception.ProductNotFoundException;
import com.zyris.caloriescalculator.service.adapters.UpdateToMessageAdapter;
import com.zyris.caloriescalculator.service.resolvers.UpdateMessageResolver;
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
                                    ResponseContext context = updateOperationResolver.resolve(telegramMessage);
                                    telegramBot.execute(new SendMessage(telegramMessage.chatId(), context.message()));
                                } catch (ProductNotFoundException | IllegalArgumentException ex) {
                                    telegramBot.execute(new SendMessage(telegramMessage.chatId(), ex.getMessage()));
                                }
                            });
                    return UpdatesListener.CONFIRMED_UPDATES_ALL;
                }
        );
    }
}
