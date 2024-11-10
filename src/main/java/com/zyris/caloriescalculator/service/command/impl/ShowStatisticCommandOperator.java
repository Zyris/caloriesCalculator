package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.dao.UserModeDAO;
import com.zyris.caloriescalculator.dao.UserRationDAO;
import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class ShowStatisticCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/stat");
    private final UserRationDAO userRationDAO;

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        userRationDAO.findByUserIdAndDay(telegramMessage.userId(), LocalDate.now());

        return new OperationContext("past product name and weight in format: <product_name> <weight>");
    }

    @Override
    public String description() {
        return "Add eaten product";
    }
}
