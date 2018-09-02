package ru.pfpay.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Format;
import ru.pfpay.config.Messages;
import ru.pfpay.json.BaseEntitySerializer;
import ru.pfpay.json.ContractDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@Entity
@Table(name = "request")
@ApiObject(name = Messages.API_OBJECT_REQUEST, description = Messages.DESCRIPTION_ORGANIZATION)
public class Request extends BaseEntityCustomId {

    public static final String CONTRACT = "contract";

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonProperty
    @JsonSerialize(using = BaseEntitySerializer.class)
    @JsonDeserialize(using = ContractDeserializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT, processtemplate=false)
    private Contract contract;

    @Column(name = "request_direction")
    @NotNull(message = Messages.ERROR_REQUEST_DIRECTION_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_DIRECTION)
    private Integer requestDirection;

    @Column(name = "request_type")
    @NotNull(message = Messages.ERROR_REQUEST_TYPE_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_TYPE)
    private Integer requestType;

    @Column(name = "request_status")
    @NotNull(message = Messages.ERROR_REQUEST_STATUS_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_STATUS)
    private Integer requestStatus;

    @Column(name = "lock_time")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_LOCK_TIME)
    private Instant lockTime;

    @Column(name = "execution_time")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_EXECUTION_TIME)
    private Instant executionTime;

    @Column(name = "request_duration")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_DURATION)
    private Long requestDuration;

    @Column(name = "error_code")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_ERROR_CODE)
    private Integer errorCode;

    @Column(name = "error_message")
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_ERROR_MESSAGE_LENGTH)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_ERROR_MESSAGE)
    private String errorMessage;

    @Column(name = "request_data")
    @Type(type = "ru.pfpay.hibernate.RequestDataType")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_REQUEST_DATA)
    private RequestData requestData;

    public RequestData createRequestData() {

        requestData = new RequestData();

        requestData.setRequestType(requestType);
        requestData.setSnils(contract.getSnils());
        requestData.setOgrn(contract.getOgrn());
        requestData.setContractNumber(contract.getContractNumber());
        requestData.setContractDate(contract.getContractDate());
        requestData.setContractBalance(contract.getContractBalance());

        return requestData;
    }

    public Long getContractId() {
        return contract != null ? contract.getId() : null;
    }

    public String getHost() {
        return contract != null ? contract.getHost() : null;
    }

    public Integer getPort() {
        return contract != null ? contract.getPort() : null;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public Integer getRequestDirection() {
        return requestDirection;
    }

    public void setRequestDirection(Integer requestDirection) {
        this.requestDirection = requestDirection;
    }

    public Integer getRequestType() {
        return requestType;
    }

    public void setRequestType(Integer requestType) {
        this.requestType = requestType;
    }

    public Integer getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(Integer requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Instant getLockTime() {
        return lockTime;
    }

    public void setLockTime(Instant lockTime) {
        this.lockTime = lockTime;
    }

    public Instant getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Instant executionTime) {
        this.executionTime = executionTime;
    }

    public Long getRequestDuration() {
        return requestDuration;
    }

    public void setRequestDuration(Long requestDuration) {
        this.requestDuration = requestDuration;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public RequestData getRequestData() {
        return requestData;
    }

    public void setRequestData(RequestData requestData) {
        this.requestData = requestData;
    }
}
