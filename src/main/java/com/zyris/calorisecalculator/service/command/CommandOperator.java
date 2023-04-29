package com.zyris.calorisecalculator.service.command;

import com.pengrad.telegrambot.model.Update;

import java.util.Set;

public abstract class CommandOperator {

    public abstract Set<String> getAllowedTypes();

    public abstract String apply(Update update);

    public boolean test(String commandMessage) {
        return getAllowedTypes().stream()
                .anyMatch(type -> type.equalsIgnoreCase(commandMessage));
    }

}
