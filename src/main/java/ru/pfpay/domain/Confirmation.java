package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;


public class Confirmation implements Serializable {

    @JsonProperty
    private String ogrn;

    @JsonProperty
    private byte[] signature;


    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }
}
