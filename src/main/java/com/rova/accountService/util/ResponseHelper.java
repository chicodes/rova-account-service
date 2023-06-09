package com.rova.accountService.util;

import com.rova.accountService.dto.RevoResponse;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class ResponseHelper<T> {

    public RevoResponse getResponse (String failedCode, String message, T body, HttpStatus httpStatus) {
        RevoResponse response = new RevoResponse();
        response.setRespCode(failedCode);
        response.setRespDescription(message);
        response.setRespBody(body);
        response.setHttpStatus(httpStatus);
        return response;
    }
}


