package com.change.money.app.controller;

import com.change.money.app.controller.dto.BaseWebDtoResponse;
import com.change.money.app.controller.dto.ExchangeRateWebDto;
import com.change.money.app.service.ExchangeRateService;
import com.change.money.app.service.dto.ExchangeRateDto;
import com.neovisionaries.i18n.CurrencyCode;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/exchange-rate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebDtoResponse<List<ExchangeRateWebDto>>>> getAllExchangeRates(@RequestParam(value = "limit", defaultValue = "5") int limit,
                                                                                                    @RequestParam(value = "page", defaultValue = "0") int page) {
        return exchangeRateService.getExchangeRates(limit, page)
                .subscribeOn(Schedulers.io())
                .map(response -> ResponseEntity.ok(BaseWebDtoResponse.successWithData(toExchangeRateWebDto(response))));
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebDtoResponse>> addExchangeRate(@RequestBody ExchangeRateWebDto exchangeRateWebDto) {
        return exchangeRateService.addExchangeRate(toExchangeRateDto(exchangeRateWebDto))
                .subscribeOn(Schedulers.io())
                .map(response -> ResponseEntity
                        .created(URI.create("/api/exchange-rate/" + response))
                        .body(BaseWebDtoResponse.successNoData()));
    }

    @PutMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebDtoResponse>> updateExchangeRate(@RequestBody ExchangeRateWebDto exchangeRateWebDto) {
        return exchangeRateService.updateExchangeRate(toExchangeRateDto(exchangeRateWebDto))
                .subscribeOn(Schedulers.io())
                .toSingle(() -> ResponseEntity.ok(BaseWebDtoResponse.successNoData()));
    }

    private ExchangeRateDto toExchangeRateDto(ExchangeRateWebDto exchangeRateWebDto) {
        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setValue(exchangeRateWebDto.getValue());
        exchangeRateDto.setOriginCurrency(exchangeRateWebDto.getOriginCurrency().name());
        exchangeRateDto.setDestinationCurrency(exchangeRateWebDto.getDestinationCurrency().name());
        return exchangeRateDto;
    }

    private List<ExchangeRateWebDto> toExchangeRateWebDto(List<ExchangeRateDto> exchangeRates) {
        List<ExchangeRateWebDto> exchangeRateList = new ArrayList<>();

        if (!CollectionUtils.isEmpty(exchangeRates)) {
            exchangeRates.forEach(exchangeRateDto -> {
                ExchangeRateWebDto exchangeRateWebDto = new ExchangeRateWebDto();
                exchangeRateWebDto.setValue(exchangeRateDto.getValue());
                exchangeRateWebDto.setOriginCurrency(CurrencyCode.valueOf(exchangeRateDto.getOriginCurrency()));
                exchangeRateWebDto.setDestinationCurrency(CurrencyCode.valueOf(exchangeRateDto.getDestinationCurrency()));

                exchangeRateList.add(exchangeRateWebDto);
            });
        }

        return exchangeRateList;
    }
}
