package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandOperator;
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
    public OperationContext apply(TelegramMessage telegramMessage) {
        return new OperationContext("not yet implemented");
    }

    @Override
    public String description() {
        return "Show help information";
    }
}
