package com.change.money.app.controller.dto;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeMoneyWebResponse {

    private String amount;
    private String amountChanged;
    private CurrencyCode originCurrency;
    private CurrencyCode destinationCurrency;
    private String exchangeRate;

}
