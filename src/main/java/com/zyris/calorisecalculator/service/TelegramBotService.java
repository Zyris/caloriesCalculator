package com.zyris.calorisecalculator.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TelegramBotService {
    private final TelegramBot telegramBot;

    public void run() {
        telegramBot.setUpdatesListener(updates -> {
            System.out.println(updates);
            long chatId = updates.get(0).message().chat().id();
            SendResponse response = telegramBot.execute(new SendMessage(chatId, "Hello!"));
            System.out.println(response);

            return UpdatesListener.CONFIRMED_UPDATES_ALL;
        });
    }

}
