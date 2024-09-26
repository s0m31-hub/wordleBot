package org.nwolfhub.wordlebot;

import com.pengrad.telegrambot.TelegramBot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class Config {
    @Bean
    public TelegramBot getTelegramBot(Environment environment) {
        return new TelegramBot(environment.getProperty("bot.token"));
    }
}
