package com.change.money.app.controller;

import com.change.money.app.controller.dto.BaseWebResponse;
import com.change.money.app.controller.dto.ExchangeRateWebRequest;
import com.change.money.app.service.ExchangeRateService;
import com.change.money.app.service.dto.ExchangeRateDto;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> addExchangeRate(@RequestBody ExchangeRateWebRequest exchangeRateWebRequest) {
        return exchangeRateService.addExchangeRate(toExchangeRateDto(exchangeRateWebRequest))
                .subscribeOn(Schedulers.io())
                .map(response -> ResponseEntity
                        .created(URI.create("/api/exchange-rate/" + response))
                        .body(BaseWebResponse.successNoData()));
    }

    private ExchangeRateDto toExchangeRateDto(ExchangeRateWebRequest exchangeRateWebRequest) {
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setValue(exchangeRateWebRequest.getValue());
        exchangeRateDto.setOriginCurrency(exchangeRateWebRequest.getOriginCurrency().name());
        exchangeRateDto.setDestinationCurrency(exchangeRateWebRequest.getDestinationCurrency().name());
        return exchangeRateDto;
    }
}
