package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.domain.TelegramMessage;
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


    public String resolve(TelegramMessage telegramMessage) {
        User user = userResolver.resolve(telegramMessage.getUserId());

        if (user.getStatus().equals(User.Status.NEED_EXTRA_INFO)) {
            user.setStatus(User.Status.NONE);
            return user.getOperationMap().get(telegramMessage.getText()).operate();
        }
        if (isCommand(telegramMessage.getText()))
            return commandChooser.chooseCommand(telegramMessage.getText())
                    .apply(telegramMessage);

        else return commonTextParserAndSave.operateUserAndHisMessage(user, telegramMessage);
    }

    private Boolean isCommand(String message) {
        return message.startsWith("/");
    }
}
