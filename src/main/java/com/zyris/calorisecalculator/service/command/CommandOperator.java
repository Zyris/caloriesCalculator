package com.zyris.calorisecalculator.service.command;

import com.zyris.calorisecalculator.domain.OperationContext;
import com.zyris.calorisecalculator.domain.TelegramMessage;

import java.util.Set;

public abstract class CommandOperator {

    public abstract Set<String> getAllowedTypes();

    public abstract OperationContext apply(TelegramMessage telegramMessage);

    public abstract String description();

    public boolean test(String commandMessage) {
        return getAllowedTypes().stream()
                .anyMatch(type -> type.equalsIgnoreCase(commandMessage));
    }

}
