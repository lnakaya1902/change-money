package com.change.money.app.controller.dto;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeRateWebRequest {

    private String value;
    private CurrencyCode originCurrency;
    private CurrencyCode destinationCurrency;

}
