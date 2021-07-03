package com.zyris.calorisecalculator.service.operator.impl;

import com.pengrad.telegrambot.model.Update;
import com.zyris.calorisecalculator.dao.ProfilesRepository;
import com.zyris.calorisecalculator.persistance.entity.ProfilesPostgreEntity;
import com.zyris.calorisecalculator.service.operator.MessageOperator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class StartMessageOperator implements MessageOperator {
    private final ProfilesRepository profilesRepository;

    @Override
    public String getKeyWord() {
        return "/start";
    }

    @Override
    public String solve(Update update) {
        profilesRepository.save(ProfilesPostgreEntity.builder()
                .idPerson(update.message().from().id())
                .idChat(update.message().chat().id())
                .build());
        return "Hi! \nВведи /help для вывода списка комманд";
    }
}
