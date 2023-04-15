package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.domain.User;
import com.zyris.calorisecalculator.service.operator.MessageOperator;
import com.zyris.calorisecalculator.service.parser.CommonTextParserAndSave;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class UpdateMessageResolver {
    Map<String, MessageOperator> messageOperatorMap;
    private final CommonTextParserAndSave commonTextParserAndSave;
    private final UserResolver userResolver;

    public UpdateMessageResolver(List<MessageOperator> messageOperatorList, CommonTextParserAndSave commonTextParserAndSave, UserResolver userResolver) {
        this.messageOperatorMap = messageOperatorList.stream()
                .collect(Collectors.toMap(MessageOperator::getKeyWord, Function.identity()));
        this.commonTextParserAndSave = commonTextParserAndSave;
        this.userResolver = userResolver;
    }

    public String resolve(Update update) {
        Long userId = update.message().from().id();
        User user = userResolver.resolve(userId);

        if (user.getStatus().equals(User.Status.NEED_EXTRA_INFO)) {
            user.setStatus(User.Status.NONE);
            return user.getOperationMap().get(update.message().text()).operate();
        }
        if (isCommand(update.message().text()))
            return messageOperatorMap
                    .getOrDefault(update.message().text(), messageOperatorMap.get("/not_exist"))
                    .solve(update);
        else return commonTextParserAndSave.operateUserAndHisMessage(user, update);
    }

    private Boolean isCommand(String message) {
        return message.startsWith("/");
    }
}
