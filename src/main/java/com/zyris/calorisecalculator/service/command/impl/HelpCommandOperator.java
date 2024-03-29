package com.zyris.calorisecalculator.service.command.impl;

import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.service.command.CommandOperator;
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
    public String apply(TelegramMessage telegramMessage) {
        return "not yet implemented";
    }
}
