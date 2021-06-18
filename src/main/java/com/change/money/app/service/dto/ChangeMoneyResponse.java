package com.change.money.app.service.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChangeMoneyResponse {

    private String amount;
    private String amountChanged;
    private String originCurrency;
    private String destinationCurrency;
    private String exchangeRate;

}
