package org.example.bank.dto;

import lombok.Data;
import lombok.ToString;
import org.example.bank.Currency;

@Data
@ToString

public class CurrencyItemDto {

    private Currency ccy;

    private Currency base_ccy;

    private float buy;

    private float sale;
}
