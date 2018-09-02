package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;
import ru.pfpay.json.LocalDateDeserializer;
import ru.pfpay.json.LocalDateSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class RequestData implements Serializable {

    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_TYPE)
    private Integer requestType;

    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_SNILS)
    private String snils;

    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_OGRN)
    private String ogrn;

    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_NUMBER)
    private String contractNumber;

    @JsonProperty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_DATE)
    private LocalDate contractDate;

    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_BALANCE)
    private BigDecimal contractBalance;

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }

    public BigDecimal getContractBalance() {
        return contractBalance;
    }

    public void setContractBalance(BigDecimal contractBalance) {
        this.contractBalance = contractBalance;
    }
}
