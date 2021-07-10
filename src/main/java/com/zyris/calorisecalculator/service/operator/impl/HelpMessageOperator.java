package com.zyris.calorisecalculator.service.operator.impl;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.service.operator.MessageOperator;
import org.springframework.stereotype.Component;

@Component
public class HelpMessageOperator implements MessageOperator {
    @Override
    public String getKeyWord() {
        return "/help";
    }

    @Override
    public String solve(Update update) {
        return "not yet implemented";
    }
}
