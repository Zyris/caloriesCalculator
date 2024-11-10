package com.zyris.caloriescalculator.configuration.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "telegram")
@Data
public class TelegramBotConfigurationProperties {
    private BotProperties bot;

    @Data
    public static class BotProperties {
        private String token;
    }

}
