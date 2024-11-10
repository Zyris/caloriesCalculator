package com.zyris.caloriescalculator.service.resolvers;

import com.zyris.caloriescalculator.repository.UserModeDAO;
import com.zyris.caloriescalculator.repository.UserOptionsDAO;
import com.zyris.caloriescalculator.repository.impl.memory.UserModeDAOInMemory;
import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.ResponseContext;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandChooser;
import com.zyris.caloriescalculator.service.parser.CommonTextParserAndSave;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateMessageResolver {
    private final CommandChooser commandChooser;
    private final CommonTextParserAndSave commonTextParserAndSave;
    private final UserModeDAO userModeDAO;
    private final UserOptionsDAO userOptionsDAO;

    public ResponseContext resolve(TelegramMessage telegramMessage) {
        UserModeDAOInMemory.Mode userMode = userModeDAO.getUserMode(telegramMessage.userId());
//        User user = userResolver.resolve(telegramMessage.userId());//todo do we really need this?

        if (userMode.equals(UserModeDAO.Mode.NONE) && telegramMessage.isCommand())
            return new ResponseContext(commandChooser.chooseCommand(telegramMessage.text())
                    .apply(telegramMessage).getMessage());

        return switch (userMode) {
            case NONE -> new ResponseContext("do nothing");
            case ADD_PRODUCT -> commonTextParserAndSave.operateUserAndHisMessage(telegramMessage);
            case CHOOSE_OPTION -> new ResponseContext(
                    userOptionsDAO.getUserOptions(telegramMessage.userId())
                            .getOrDefault(telegramMessage.text(),
                                    () -> {
                                        userModeDAO.setUserMode(telegramMessage.userId(), UserModeDAO.Mode.NONE);
                                        return new OperationContext("no options is found");
                                    })
                            .operate()
                            .getMessage()

            );
        };
    }

}
