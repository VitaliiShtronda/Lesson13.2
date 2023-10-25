package org.example.bank.impl;

import org.example.bank.Currency;

import org.example.bank.CurrencyPrettier;
import org.example.bank.CurrencyService;

public class PrivatBankCurrencyServiceTest {
    public static void main(String[] args) {
        CurrencyService privatBankCurrencyService = new PrivatBankCurrencyService();
        double rate = privatBankCurrencyService.getRate(Currency.USD);
        System.out.println("privatBankCurrencyService.getRate(Currency.USD) = " + rate);

        CurrencyPrettier currencyPrettier = new CurrencyPrettierImpl();
        System.out.println("currencyPrettier.pretty(rate) = " + currencyPrettier.pretty(rate, Currency.USD));


    }
}