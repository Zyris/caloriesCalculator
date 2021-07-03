package com.zyris.calorisecalculator.service.operator.impl;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.service.operator.MessageOperator;
import org.springframework.stereotype.Component;

@Component
public class NotExistMessageOperator implements MessageOperator {
    @Override
    public String getKeyWord() {
        return "/not_exist";
    }

    @Override
    public String solve(Update update) {
        return "Пожалуйста, воспользуйтесь /help для вывода списка комманд";
    }
}
