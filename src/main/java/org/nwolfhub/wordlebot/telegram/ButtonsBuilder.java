package org.nwolfhub.wordlebot.telegram;

import com.pengrad.telegrambot.model.request.InlineKeyboardButton;
import com.pengrad.telegrambot.model.request.InlineKeyboardMarkup;
import com.pengrad.telegrambot.model.request.Keyboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ButtonsBuilder {
    private final LanguageProvider languageProvider;

    public ButtonsBuilder(LanguageProvider languageProvider) {
        this.languageProvider = languageProvider;
    }
    public Keyboard menu() {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        markup.addRow(new InlineKeyboardButton());
    }
}
