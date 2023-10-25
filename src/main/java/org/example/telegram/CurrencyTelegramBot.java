package org.example.telegram;

import org.example.bank.Currency;
import org.example.bank.CurrencyPrettier;
import org.example.bank.CurrencyService;
import org.example.bank.impl.CurrencyPrettierImpl;
import org.example.bank.impl.PrivatBankCurrencyService;
import org.example.telegram.command.HelpCommand;
import org.example.telegram.command.StartCommand;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class CurrencyTelegramBot extends TelegramLongPollingCommandBot {

    private CurrencyService currencyService;
    private CurrencyPrettier currencyPrettier;

    public CurrencyTelegramBot(){

        this.currencyService = new PrivatBankCurrencyService();
        this.currencyPrettier = new CurrencyPrettierImpl();

        register(new HelpCommand());
        register(new StartCommand());
    }

    @Override
    public String getBotUsername() {
        return LoginConstants.NAME;
    }

    @Override
    public String getBotToken() {
        return LoginConstants.TOKEN;
    }

    @Override
    public void processNonCommandUpdate(Update update) {
        if (update.hasCallbackQuery()){
            String callbackData = update.getCallbackQuery().getData();

            String rate = getRate(Currency.valueOf(callbackData));

            SendMessage sm = new SendMessage();
            sm.setText(rate);
            sm.setChatId(update.getCallbackQuery().getMessage().getChatId());
            sm.setReplyMarkup(ButtonCreationService.getButtons());


            try {
                execute(sm);
            } catch (TelegramApiException e) {
                System.out.println("error while responding");
            }
        }
        if (update.hasMessage()) {
            String msg = "You just wrote + " + update.getMessage().getText();
            System.out.println(msg);
        }

    }
    private String getRate(Currency c) {
        return currencyPrettier.pretty(currencyService.getRate(c), c);
    }
}
