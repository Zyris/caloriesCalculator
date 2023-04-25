package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.service.operator.CommandOperator;
import com.zyris.calorisecalculator.service.operator.impl.DefaultNotExistCommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandService {
    private final List<CommandOperator> commandOperatorList;
    private final DefaultNotExistCommandOperator defaultOperator;

    public String apply(Update update) {
        return commandOperatorList.stream()
                .filter(op->!defaultOperator.equals(op))
                .filter(op -> op.test(update.message().text()))
                .findFirst()
                .orElse(defaultOperator)
                .apply(update);
    }

}
