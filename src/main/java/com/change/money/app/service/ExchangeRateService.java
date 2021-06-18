package com.change.money.app.service;

import com.change.money.app.service.dto.ExchangeRateDto;
import io.reactivex.Single;

public interface ExchangeRateService {

    Single<Long> addExchangeRate(ExchangeRateDto exchangeRateDto);
}
