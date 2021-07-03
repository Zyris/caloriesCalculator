package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.service.operator.MessageOperator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UpdateMessageResolver {
    Map<String, MessageOperator> messageOperatorMap;

    public UpdateMessageResolver(List<MessageOperator> messageOperatorList) {
        this.messageOperatorMap = messageOperatorList.stream()
                .collect(Collectors.toMap(MessageOperator::getKeyWord, Function.identity()));
    }

    public String resolve(Update update) {
        MessageOperator messageOperator = messageOperatorMap.containsKey(update.message().text()) ?
                messageOperatorMap.get(update.message().text()) :
                messageOperatorMap.get("/not_exist");
        return messageOperator.solve(update);
    }
}
