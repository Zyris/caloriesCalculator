package com.zyris.calorisecalculator.service.command.impl;

import com.zyris.calorisecalculator.dao.ProfilesRepository;
import com.zyris.calorisecalculator.domain.TelegramMessage;
import com.zyris.calorisecalculator.persistance.entity.ProfilesPostgreEntity;
import com.zyris.calorisecalculator.service.command.CommandOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class StartCommandOperator extends CommandOperator {
    private static final Set<String> ALLOWED_TYPES = Set.of("/start");
    private final ProfilesRepository profilesRepository;

    @Override
    public Set<String> getAllowedTypes() {
        return ALLOWED_TYPES;
    }

    @Override
    public String apply(TelegramMessage telegramMessage) {
        profilesRepository.save(ProfilesPostgreEntity.builder()
                .idPerson(telegramMessage.userId())
                .idChat(telegramMessage.chatId())
                .build());
        return "Hi! \nType /help to see command list";
    }
}
