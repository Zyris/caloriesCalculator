package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.domain.User;
import com.zyris.calorisecalculator.service.command.CommandChooser;
import com.zyris.calorisecalculator.service.parser.CommonTextParserAndSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMessageResolver {
    private final CommandChooser commandChooser;
    private final CommonTextParserAndSave commonTextParserAndSave;
    private final UserResolver userResolver;


    public String resolve(Update update) {
        Long userId = update.message().from().id();
        User user = userResolver.resolve(userId);

        if (user.getStatus().equals(User.Status.NEED_EXTRA_INFO)) {
            user.setStatus(User.Status.NONE);
            return user.getOperationMap().get(update.message().text()).operate();
        }
        if (isCommand(update.message().text()))
            return commandChooser.chooseCommand(update.message().text())
                    .apply(update);

        else return commonTextParserAndSave.operateUserAndHisMessage(user, update);
    }

    private Boolean isCommand(String message) {
        return message.startsWith("/");
    }
}
