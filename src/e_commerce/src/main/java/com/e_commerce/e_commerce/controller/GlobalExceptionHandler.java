package com.e_commerce.e_commerce.controller;

import com.e_commerce.e_commerce.model.ApiReturnData;
import com.e_commerce.e_commerce.model.ApiStatus;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiReturnData> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();

        ApiReturnData response = new ApiReturnData();
        response.setApiResponseStatus(ApiStatus.ERROR);

        if (cause instanceof InvalidFormatException && cause.getMessage().contains("Role")) {
            response.setApiResponseMessage("Invalid role provided. Accepted values: ADMIN, CUSTOMER");
        } else {
            response.setApiResponseMessage("Malformed request. Please check your input format.");
        }

        response.setApiResponseData(null);
        return ResponseEntity.badRequest().body(response);
    }
}
