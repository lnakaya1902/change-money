package com.change.money.app.service.dto;

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
    private String originCurrency;
    private String destinationCurrency;

}
