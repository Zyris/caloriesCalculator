package com.zyris.calorisecalculator.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.zyris.calorisecalculator.configuration.properties.TelegramBotConfigurationProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TelegramBotAutoConfiguration {
    @Bean
    TelegramBot telegramBot(TelegramBotConfigurationProperties properties) {
        System.out.println("token " + properties.getBot().getToken());
        return new TelegramBot(properties.getBot().getToken());

    }
}
