package com.change.money.app.service;

import com.change.money.app.service.dto.ExchangeRateDto;
import io.reactivex.Completable;
import io.reactivex.Single;

import java.util.List;

public interface ExchangeRateService {

    Single<Long> addExchangeRate(ExchangeRateDto exchangeRateDto);

    Completable updateExchangeRate(ExchangeRateDto exchangeRateDto);

    Single<List<ExchangeRateDto>> getExchangeRates(int limit, int page);
}
