package com.change.money.app.controller;

import com.change.money.app.controller.dto.BaseWebResponse;
import com.change.money.app.controller.dto.ChangeMoneyWebRequest;
import io.reactivex.Single;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/change-money")
public class ChangeMoneyController {

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Single<ResponseEntity<BaseWebResponse>> changeMoney(@RequestBody ChangeMoneyWebRequest changeMoneyWebRequest) {
        return null;
    }
}
