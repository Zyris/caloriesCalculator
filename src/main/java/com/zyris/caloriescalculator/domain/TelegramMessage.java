package com.zyris.caloriescalculator.domain;

import lombok.Builder;

@Builder
public record TelegramMessage(String text, Long userId, Long chatId) {
    public boolean isCommand() {
        return text.startsWith("/");
    }
}
