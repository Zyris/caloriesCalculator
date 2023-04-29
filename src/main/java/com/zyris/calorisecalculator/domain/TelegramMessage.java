package com.zyris.calorisecalculator.domain;

import com.pengrad.telegrambot.model.Update;
import lombok.Getter;

@Getter
public class TelegramMessage {
    private String text;
    private Long userId;
    private Long chatId;

    public static TelegramMessage from(Update update) {
        TelegramMessage telegramMessage = new TelegramMessage();
        telegramMessage.text = update.message().text();
        telegramMessage.userId = update.message().from().id();
        telegramMessage.chatId = update.message().chat().id();
        return telegramMessage;
    }
}
