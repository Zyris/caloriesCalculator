package com.zyris.calorisecalculator;

import com.zyris.calorisecalculator.service.TelegramBotService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CaloriesCalculatorApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CaloriesCalculatorApplication.class, args);

        context.getBean(TelegramBotService.class).run();
    }
}
