package com.zyris.calorisecalculator.service.command.impl;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.dao.ProfilesRepository;
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
    public String apply(Update update) {
        profilesRepository.save(ProfilesPostgreEntity.builder()
                .idPerson(update.message().from().id())
                .idChat(update.message().chat().id())
                .build());
        return "Hi! \nType /help to see command list";
    }
}
