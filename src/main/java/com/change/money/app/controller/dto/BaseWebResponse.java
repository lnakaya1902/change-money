package com.change.money.app.controller.dto;

import com.change.money.app.exception.ErrorCodeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseWebResponse<T> {
    private ErrorCodeEnum errorCode;
    private T data;

    public static BaseWebResponse successNoData() {
        return BaseWebResponse.builder()
                .build();
    }

    public static <T> BaseWebResponse<T> successWithData(T data) {
        return BaseWebResponse.<T>builder()
                .data(data)
                .build();
    }

    public static BaseWebResponse error(ErrorCodeEnum errorCode) {
        return BaseWebResponse.builder()
                .errorCode(errorCode)
                .build();
    }
}
