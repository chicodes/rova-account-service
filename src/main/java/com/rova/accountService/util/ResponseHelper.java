package com.rova.accountService.util;

import com.rova.accountService.dto.AccountResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper<T> {

    public AccountResponse getResponse (String failedCode, String message, T body, HttpStatus httpStatus) {
        AccountResponse response = new AccountResponse();
        response.setRespCode(failedCode);
        response.setRespDescription(message);
        response.setRespBody(body);
        response.setHttpStatus(httpStatus);
        return response;
    }
}


