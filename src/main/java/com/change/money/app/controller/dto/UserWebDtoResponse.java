package com.change.money.app.controller.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserWebDtoResponse {

    private String user;
    private String token;
}
