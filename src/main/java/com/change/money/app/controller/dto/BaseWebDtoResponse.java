package com.change.money.app.controller.dto;

import com.change.money.app.exception.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseWebDtoResponse<T> {
    private ErrorCodeEnum errorCode;
    private T data;

    public static BaseWebDtoResponse successNoData() {
        return BaseWebDtoResponse.builder()
                .build();
    }

    public static <T> BaseWebDtoResponse<T> successWithData(T data) {
        return BaseWebDtoResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BaseWebDtoResponse error(ErrorCodeEnum errorCode) {
        return BaseWebDtoResponse.builder()
                .errorCode(errorCode)
                .build();
    }
}
