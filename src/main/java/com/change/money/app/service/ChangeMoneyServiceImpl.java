package com.change.money.app.service;

import com.change.money.app.entity.ExchangeRate;
import com.change.money.app.repository.ExchangeRateRepository;
import com.change.money.app.service.dto.ChangeMoneyRequest;
import com.change.money.app.service.dto.ChangeMoneyResponse;
import com.change.money.app.util.NumberUtil;
import io.reactivex.Single;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static com.change.money.app.util.NumberUtil.TRUNCATE_DECIMAL_TWO;

@Service
public class ChangeMoneyServiceImpl implements ChangeMoneyService {

    private final ExchangeRateRepository exchangeRateRepository;

    public ChangeMoneyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public Single<ChangeMoneyResponse> applyExchangeRate(ChangeMoneyRequest changeMoneyRequest) {
        return Single.create(singleSubscriber -> {
            List<ExchangeRate> exchangeRates = exchangeRateRepository.findByOriginCurrencyAndDestinationCurrency(changeMoneyRequest.getOriginCurrency(), changeMoneyRequest.getDestinationCurrency());
            Optional<ExchangeRate> exchangeRate = exchangeRates.stream().findFirst();
            if (exchangeRate.isEmpty())
                singleSubscriber.onError(new EntityNotFoundException());
            else {
                ChangeMoneyResponse changeMoneyResponse = toChangeMoneyResponse(changeMoneyRequest, exchangeRate.get());
                singleSubscriber.onSuccess(changeMoneyResponse);
            }
        });
    }

    private ChangeMoneyResponse toChangeMoneyResponse(ChangeMoneyRequest changeMoneyRequest, ExchangeRate exchangeRate) {
        ChangeMoneyResponse changeMoneyResponse = new ChangeMoneyResponse();
        changeMoneyResponse.setAmount(changeMoneyRequest.getAmount());
        changeMoneyResponse.setOriginCurrency(changeMoneyRequest.getOriginCurrency());
        changeMoneyResponse.setDestinationCurrency(changeMoneyRequest.getDestinationCurrency());
        changeMoneyResponse.setExchangeRate(exchangeRate.getValue());
        changeMoneyResponse.setAmountChanged(NumberUtil.multiply(changeMoneyRequest.getAmount(), exchangeRate.getValue(), TRUNCATE_DECIMAL_TWO));
        return changeMoneyResponse;
    }
}
