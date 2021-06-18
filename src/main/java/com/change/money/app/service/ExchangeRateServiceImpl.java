package com.change.money.app.service;

import com.change.money.app.entity.ExchangeRate;
import com.change.money.app.repository.ExchangeRateRepository;
import com.change.money.app.service.dto.ExchangeRateDto;
import io.reactivex.Completable;
import io.reactivex.Single;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public Completable updateExchangeRate(ExchangeRateDto exchangeRateDto) {
        return Completable.create(completableSubscriber -> {
            List<ExchangeRate> exchangeRates = exchangeRateRepository.findByOriginCurrencyAndDestinationCurrency(exchangeRateDto.getOriginCurrency(), exchangeRateDto.getDestinationCurrency());
            Optional<ExchangeRate> exchangeRate = exchangeRates.stream().findFirst();
            if (exchangeRate.isEmpty())
                completableSubscriber.onError(new EntityNotFoundException());
            else {
                ExchangeRate exchangeRateForUpdate = exchangeRate.get();
                exchangeRateForUpdate.setValue(exchangeRateDto.getValue());
                exchangeRateRepository.save(exchangeRateForUpdate);
                completableSubscriber.onComplete();
            }
        });
    }

    @Override
    public Single<List<ExchangeRateDto>> getExchangeRates(int limit, int page) {
        return Single.create(singleSubscriber -> {
            List<ExchangeRate> exchangeRates = exchangeRateRepository.findAll(PageRequest.of(page, limit)).getContent();
            List<ExchangeRateDto> exchangeRateResponse = exchangeRates.stream().map(this::toExchangeRateDto).collect(Collectors.toList());
            singleSubscriber.onSuccess(exchangeRateResponse);
        });
    }

    private ExchangeRate toExchangeRate(ExchangeRateDto exchangeRateDto) {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setOriginCurrency(exchangeRateDto.getOriginCurrency());
        exchangeRate.setDestinationCurrency(exchangeRateDto.getDestinationCurrency());
        exchangeRate.setValue(exchangeRateDto.getValue());
        return exchangeRate;
    }

    private ExchangeRateDto toExchangeRateDto(ExchangeRate exchangeRate) {
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setOriginCurrency(exchangeRate.getOriginCurrency());
        exchangeRateDto.setDestinationCurrency(exchangeRate.getDestinationCurrency());
        exchangeRateDto.setValue(exchangeRate.getValue());
        return exchangeRateDto;
    }
}
