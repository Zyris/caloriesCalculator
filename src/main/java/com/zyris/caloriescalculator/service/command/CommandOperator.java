package com.zyris.caloriescalculator.service.command;

import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;

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
