package com.zyris.calorisecalculator.configuration;

import com.pengrad.telegrambot.TelegramBot;
import com.zyris.calorisecalculator.configuration.properties.TelegramBotConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(TelegramBotConfigurationProperties.class)
public class TelegramBotAutoConfiguration {
    @Bean
    TelegramBot telegramBot(TelegramBotConfigurationProperties properties) {
        return new TelegramBot(properties.getBot().getToken());
    }
}
