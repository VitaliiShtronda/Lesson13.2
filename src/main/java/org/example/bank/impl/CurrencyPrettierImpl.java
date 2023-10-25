package org.example.bank.impl;

import org.example.bank.Currency;
import org.example.bank.CurrencyPrettier;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class CurrencyPrettierImpl implements CurrencyPrettier {
    public static final String FORMAT = "Курс валюти %s до %s = %s";

    @Override
    public String pretty(double rate, Currency currency) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);
        String formattedRate = df.format(rate);

        return String.format(FORMAT, currency, Currency.UAH, formattedRate);
    }
}
