package com.zyris.calorisecalculator.service.adapters;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import org.springframework.stereotype.Component;

@Component
public class UpdateToMessageAdapter {
    public TelegramMessage map (Update update) {
        return TelegramMessage.builder()
                .text(update.message().text())
                .userId(update.message().from().id())
                .chatId(update.message().chat().id())
                .build();
    }
}
