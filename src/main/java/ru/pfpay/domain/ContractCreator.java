package ru.pfpay.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;
import ru.pfpay.json.LocalDateDeserializer;
import ru.pfpay.json.LocalDateSerializer;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@ApiObject(name = Messages.API_OBJECT_CONTRACT_CREATOR, description = Messages.DESCRIPTION_CONTRACT_CREATOR)
public class ContractCreator implements Serializable {

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_SNILS_EMPTY)
    @ApiObjectField(description = Messages.DESCRIPTION_SNILS)
    private String snils;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_CONTRACT_NUMBER_EMPTY)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_NUMBER)
    private String contractNumber;

    @JsonProperty
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @NotNull(message = Messages.ERROR_CONTRACT_DATE_EMPTY)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_DATE)
    private LocalDate contractDate;

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
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
}
