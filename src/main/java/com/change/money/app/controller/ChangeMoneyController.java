package com.change.money.app.controller;

import com.change.money.app.controller.dto.BaseWebDtoResponse;
import com.change.money.app.controller.dto.ChangeMoneyWebDtoRequest;
import com.change.money.app.service.ChangeMoneyService;
import com.change.money.app.service.dto.ChangeMoneyRequest;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/change-money")
public class ChangeMoneyController {

    private final ChangeMoneyService changeMoneyService;

    public ChangeMoneyController(ChangeMoneyService changeMoneyService) {
        this.changeMoneyService = changeMoneyService;
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebDtoResponse>> applyExchangeRate(@RequestBody ChangeMoneyWebDtoRequest changeMoneyWebDtoRequest) {
        return changeMoneyService.applyExchangeRate(toChangeMoneyRequest(changeMoneyWebDtoRequest))
                .subscribeOn(Schedulers.io())
                .map(changeMoneyResponse -> ResponseEntity.ok(BaseWebDtoResponse.successWithData(changeMoneyResponse)));
    }

    private ChangeMoneyRequest toChangeMoneyRequest(ChangeMoneyWebDtoRequest changeMoneyWebDtoRequest) {
        ChangeMoneyRequest changeMoneyRequest = new ChangeMoneyRequest();
        changeMoneyRequest.setAmount(changeMoneyWebDtoRequest.getAmount());
        changeMoneyRequest.setOriginCurrency(changeMoneyWebDtoRequest.getOriginCurrency().name());
        changeMoneyRequest.setDestinationCurrency(changeMoneyWebDtoRequest.getDestinationCurrency().name());
        return changeMoneyRequest;
    }
}
