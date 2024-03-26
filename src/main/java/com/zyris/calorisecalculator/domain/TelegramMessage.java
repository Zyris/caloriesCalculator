package com.zyris.calorisecalculator.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
public record TelegramMessage(String text, Long userId, Long chatId) {
    public boolean isCommand() {
        return text.startsWith("/");
    }
}
