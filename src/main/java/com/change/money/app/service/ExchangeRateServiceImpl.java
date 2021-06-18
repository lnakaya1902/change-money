package com.change.money.app.service;

import com.change.money.app.entity.ExchangeRate;
import com.change.money.app.repository.ExchangeRateRepository;
import com.change.money.app.service.dto.ExchangeRateDto;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Single<Long> addExchangeRate(ExchangeRateDto exchangeRateDto) {
        return Single.create(singleSubscriber -> {
            Long exchangeRateId = exchangeRateRepository.save(toExchangeRate(exchangeRateDto)).getId();
            singleSubscriber.onSuccess(exchangeRateId);
        });
    }

    private ExchangeRate toExchangeRate(ExchangeRateDto exchangeRateDto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setOriginCurrency(exchangeRateDto.getOriginCurrency());
        exchangeRate.setDestinationCurrency(exchangeRateDto.getDestinationCurrency());
        exchangeRate.setValue(exchangeRateDto.getValue());
        return exchangeRate;
    }
}
