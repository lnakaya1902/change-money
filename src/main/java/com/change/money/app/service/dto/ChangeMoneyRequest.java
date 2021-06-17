package com.change.money.app.service.dto;

import com.neovisionaries.i18n.CurrencyCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeMoneyRequest {

    private String amount;
    private CurrencyCode originCurrency;
    private CurrencyCode destinationCurrency;

}
