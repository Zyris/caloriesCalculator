package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.dao.UserModeDAO;
import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class EatProductCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/eat");
    private final UserModeDAO userModeDAO;

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        userModeDAO.setUserMode(telegramMessage.userId(), UserModeDAO.Mode.ADD_PRODUCT);
        return new OperationContext("past product name and weight in format: <product_name> <weight>");
    }

    @Override
    public String description() {
        return "Add eaten product";
    }
}
