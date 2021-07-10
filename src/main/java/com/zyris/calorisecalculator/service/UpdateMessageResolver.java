package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.domain.UserState;
import com.zyris.calorisecalculator.service.container.UserChoosesContainer;
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
    private final UserChoosesContainer userChoosesContainer;

    public UpdateMessageResolver(List<MessageOperator> messageOperatorList, CommonTextParserAndSave commonTextParserAndSave, UserChoosesContainer userChoosesContainer) {
        this.messageOperatorMap = messageOperatorList.stream()
                .collect(Collectors.toMap(MessageOperator::getKeyWord, Function.identity()));
        this.commonTextParserAndSave = commonTextParserAndSave;
        this.userChoosesContainer = userChoosesContainer;
    }

    public String resolve(Update update) {
        System.out.println(userChoosesContainer.getUserIdToUserStateMap());
        if (update.message().text().startsWith("/")) {
            if (userChoosesContainer != null &&
                    userChoosesContainer.getUserIdToUserStateMap()!=null&&
                    userChoosesContainer.getUserIdToUserStateMap().containsKey(update.message().from().id()) &&
                    userChoosesContainer.getUserIdToUserStateMap().get(update.message().from().id()).getStatus().equals(UserState.Status.NEED_EXTRA_INFO)) {
                userChoosesContainer.getUserIdToUserStateMap().get(update.message().from().id()).setStatus(UserState.Status.NONE);
                return userChoosesContainer.getUserIdToUserStateMap().get(update.message().from().id()).getOperationMap().get(update.message().text()).operate();
            } else {
                MessageOperator messageOperator = messageOperatorMap.containsKey(update.message().text()) ?
                        messageOperatorMap.get(update.message().text()) :
                        messageOperatorMap.get("/not_exist");
                return messageOperator.solve(update);
            }
        } else {
            return commonTextParserAndSave.parseAndSave(update);
        }
    }
}
