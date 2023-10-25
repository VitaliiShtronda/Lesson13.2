package org.example.bank.impl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.bank.Currency;
import org.example.bank.CurrencyService;
import org.example.bank.dto.CurrencyItemDto;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class PrivatBankCurrencyService implements CurrencyService {

    @Override
    public double getRate(Currency c) {
        String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

        try {
            String json = Jsoup.connect(url)
                    .ignoreContentType(true)
                    .get()
                    .body()
                    .text();

            Type typeConverter = TypeToken.getParameterized(List.class, CurrencyItemDto.class)
                    .getType();
            List<CurrencyItemDto> items = new Gson().fromJson(json, typeConverter);

            return items.stream()
                    .filter(it -> it.getBase_ccy() == Currency.UAH)
                    .filter(it -> it.getCcy() == c)
                    .map(it -> it.getBuy())
                    .findFirst()
                    .orElseThrow();

        } catch (IOException e) {
            System.out.println("ERROR while attempt to get currency rate");
        }
        return -1;
    }
}
