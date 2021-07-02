package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendGame;
import com.zyris.calorisecalculator.dao.CaloriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
    private final TelegramBot telegramBot;
    private final CaloriesRepository caloriesRepository;


    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            System.out.println(updates);
            long chatId = updates.get(0).message().chat().id();
            System.out.println(caloriesRepository.findById(1));
            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

}
