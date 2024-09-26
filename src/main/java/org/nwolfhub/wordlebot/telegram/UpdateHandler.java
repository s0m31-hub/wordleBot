package org.nwolfhub.wordlebot.telegram;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.UpdatesListener;
import com.pengrad.telegrambot.model.Update;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.request.SendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UpdateHandler {
    private final TelegramBot bot;
    private final LanguageProvider languageProvider;

    public UpdateHandler(TelegramBot bot, LanguageProvider languageProvider) {
        this.bot = bot;
        this.languageProvider = languageProvider;
    }

    private void startUpdateListener() {
        bot.setUpdatesListener(new UpdatesListener() {
            @Override
            public int process(List<Update> list) {
                for(Update update:list) {
                    new Thread(() -> {
                        processUpdate(update);
                    }).start();
                }
                return CONFIRMED_UPDATES_ALL;
            }
        });
    }

    private void processUpdate(Update update) {
        if(update.message()!=null) {
            processMessage(update);
        }
    }

    private void processMessage(Update update) {
        User from = update.message().from();
        String lang = from.languageCode();
        String text = update.message().text();
        if(text!=null) {
            switch (text) {
                case "/start" -> {
                    languageProvider.getKey(lang, "start");
                }
            }
        }
    }
}
