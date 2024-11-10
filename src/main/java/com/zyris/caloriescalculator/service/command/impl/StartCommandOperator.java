package com.zyris.caloriescalculator.service.command.impl;

import com.zyris.caloriescalculator.repository.ProfilesDAO;
import com.zyris.caloriescalculator.domain.OperationContext;
import com.zyris.caloriescalculator.domain.dao.Profile;
import com.zyris.caloriescalculator.domain.TelegramMessage;
import com.zyris.caloriescalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/start");
    private final ProfilesDAO profilesDAO;

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public OperationContext apply(TelegramMessage telegramMessage) {
        profilesDAO.save(Profile.builder()
                .idPerson(telegramMessage.userId())
                .idChat(telegramMessage.chatId())
                .build());
        return new OperationContext("Hi! \nType /help to see command list");
    }

    @Override
    public String description() {
        return "Should not be seen";
    }
}
