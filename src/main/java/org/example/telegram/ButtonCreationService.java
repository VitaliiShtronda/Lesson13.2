package org.example.telegram;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.Arrays;
import java.util.Collections;

public class ButtonCreationService {

    public static InlineKeyboardMarkup getButtons() {
        InlineKeyboardButton usdBtn = InlineKeyboardButton
                .builder()
                .text("USD rate")
                .callbackData("USD")
                .build();

        InlineKeyboardButton eurBtn = InlineKeyboardButton
                .builder()
                .text("EUR rate")
                .callbackData("EUR")
                .build();

        return InlineKeyboardMarkup.builder()
                .keyboard(Collections.singletonList(Arrays.asList(usdBtn, eurBtn)))
                .build();

    }
}

