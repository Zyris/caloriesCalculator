package com.zyris.calorisecalculator.service.command;

import com.zyris.calorisecalculator.service.command.CommandOperator;
import com.zyris.calorisecalculator.service.command.impl.DefaultNotExistCommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandChooser {
    private final List<CommandOperator> commandOperatorList;
    private final DefaultNotExistCommandOperator defaultOperator;

    public CommandOperator chooseCommand(String message) {
        return commandOperatorList.stream()
                .filter(op->!defaultOperator.equals(op))
                .filter(op -> op.test(message))
                .findFirst()
                .orElse(defaultOperator);
    }

}
