package com.zyris.calorisecalculator.service.command.impl;

import com.zyris.calorisecalculator.dao.ProfilesDAO;
import com.zyris.calorisecalculator.domain.OperationContext;
import com.zyris.calorisecalculator.domain.Profile;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class EatProductCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/ep");
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
        return "Add eaten product";
    }
}
