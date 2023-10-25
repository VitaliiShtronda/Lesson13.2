package org.example.telegram.command;

import org.telegram.telegrambots.extensions.bots.commandbot.commands.BotCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.bots.AbsSender;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class HelpCommand extends BotCommand {

    public HelpCommand() {
        super("help", "help command description");
    }


    @Override
    public void execute(AbsSender absSender, User user, Chat chat, String[] strings) {
        SendMessage sm = new SendMessage();
        sm.setText("We can help you to get currency rate " + user.getId());
        System.out.println("We can help you to get currency rate " + user.getId());
        sm.setChatId(chat.getId());

        try {
            absSender.execute(sm);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
