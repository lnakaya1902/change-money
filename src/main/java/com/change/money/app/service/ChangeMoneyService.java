package com.change.money.app.service;

import com.change.money.app.service.dto.ChangeMoneyRequest;
import com.change.money.app.service.dto.ChangeMoneyResponse;
import io.reactivex.Single;

public interface ChangeMoneyService {

    Single<ChangeMoneyResponse> applyExchangeRate(ChangeMoneyRequest changeMoneyRequest);
}
