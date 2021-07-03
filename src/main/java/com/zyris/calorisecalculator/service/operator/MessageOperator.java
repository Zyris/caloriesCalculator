package com.zyris.calorisecalculator.service.operator;

import com.pengrad.telegrambot.model.Update;

public interface MessageOperator {
    String getKeyWord();

    String solve(Update update);

}
