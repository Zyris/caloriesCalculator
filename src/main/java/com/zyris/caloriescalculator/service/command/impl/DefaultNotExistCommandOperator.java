package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DefaultNotExistCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/not_exist");

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public boolean test(String commandMessage) {
        return true;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        return new OperationContext("Use /help to see command list");
    }

    @Override
    public String description() {
        return "delete maybe this type of command?";
    }
}
