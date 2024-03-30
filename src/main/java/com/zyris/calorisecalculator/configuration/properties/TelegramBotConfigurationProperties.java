package com.zyris.calorisecalculator.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "telegram")
@Data
public class TelegramBotConfigurationProperties {
    private BotProperties bot;

    @Data
    public static class BotProperties {
        private String token;
    }

}
