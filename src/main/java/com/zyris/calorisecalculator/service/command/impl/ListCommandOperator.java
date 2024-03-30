package com.zyris.calorisecalculator.service.command.impl;

import com.zyris.calorisecalculator.domain.OperationContext;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ListCommandOperator extends CommandOperator {
    private final List<CommandOperator> allOperators;
    private static final Set<Class> FORBIDDEN_TO_SHOW_OPERATORS = Set.of(
            DefaultNotExistCommandOperator.class,
            StartCommandOperator.class
    );
    private static final Set<String> ALLOWED_TYPES = Set.of("/list");

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        String listOfCommand = allOperators.stream()
                .filter(s -> !FORBIDDEN_TO_SHOW_OPERATORS.contains(s.getClass()))
                .map(s -> "%s: %s".formatted(s.description(), s.getAllowedTypes()))
                .collect(Collectors.joining("\n"));
        return new OperationContext("Next commands are allowed:\n%s".formatted(listOfCommand));
    }

    @Override
    public String description() {
        return "Showing list of command";
    }

}
