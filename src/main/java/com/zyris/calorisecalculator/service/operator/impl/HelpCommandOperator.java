package com.zyris.calorisecalculator.service.operator.impl;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.service.operator.CommandOperator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class HelpCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/help", "/h");

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public String apply(Update update) {
        return "not yet implemented";
    }
}
