package com.zyris.calorisecalculator.service.command;

import com.zyris.calorisecalculator.domain.TelegramMessage;

import java.util.Set;

public abstract class CommandOperator {

    public abstract Set<String> getAllowedTypes();

    public abstract String apply(TelegramMessage telegramMessage);

    public boolean test(String commandMessage) {
        return getAllowedTypes().stream()
                .anyMatch(type -> type.equalsIgnoreCase(commandMessage));
    }

}
