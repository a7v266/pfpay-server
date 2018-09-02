package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class ResponseData implements Serializable {

    @JsonProperty
    private Integer errorCode;

    @JsonProperty
    private String errorMessage;

    public ResponseData() {
    }

    public boolean hasError() {
        return errorCode != null;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
