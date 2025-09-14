package com.e_commerce.e_commerce.model;

import org.springframework.stereotype.Component;

@Component
public class ApiReturnData {

    private ApiStatus apiResponseStatus;
    private String apiResponseMessage;
    private Object apiResponseData;

    public ApiReturnData() {}

    public ApiReturnData(ApiStatus status, String message, Object data) {
        this.apiResponseStatus = status;
        this.apiResponseMessage = message;
        this.apiResponseData = data;
    }

    public ApiStatus getApiResponseStatus() {
        return apiResponseStatus;
    }

    public void setApiResponseStatus(ApiStatus apiResponseStatus) {
        this.apiResponseStatus = apiResponseStatus;
    }

    public String getApiResponseMessage() {
        return apiResponseMessage;
    }

    public void setApiResponseMessage(String apiResponseMessage) {
        this.apiResponseMessage = apiResponseMessage;
    }

    public Object getApiResponseData() {
        return apiResponseData;
    }

    public void setApiResponseData(Object apiResponseData) {
        this.apiResponseData = apiResponseData;
    }
}
