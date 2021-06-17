package com.change.money.app.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.neovisionaries.i18n.CurrencyCode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMoneyWebRequest {

    private String amount;
    private CurrencyCode originCurrency;
    private CurrencyCode destinationCurrency;

}
