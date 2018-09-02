package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;
import ru.pfpay.json.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "contract", indexes = {
        @Index(columnList = "organization_id, contract_number", unique = true)
})
@ApiObject(name = Messages.API_OBJECT_CONTRACT, description = Messages.DESCRIPTION_CONTRACT)
public class Contract extends BaseEntityCustomId {

    public static final String PERSON = "person";
    public static final String ORGANIZATION = "organization";
    public static final String CONTRACT_NUMBER = "contractNumber";

    @ManyToOne
    @JoinColumn(name = "person_id")
    @NotNull(message = Messages.ERROR_PERSON_EMPTY)
    @JsonProperty
    @JsonSerialize(using = BaseEntitySerializer.class)
    @JsonDeserialize(using = PersonDeserializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_PERSON, processtemplate=false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    @NotNull(message = Messages.ERROR_ORGANIZATION_EMPTY)
    @JsonProperty
    @JsonSerialize(using = BaseEntitySerializer.class)
    @JsonDeserialize(using = OrganizationDeserializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_ORGANIZATION, processtemplate=false)
    private Organization organization;

    @Column(name = "contract_number")
    @NotEmpty(message = Messages.ERROR_CONTRACT_NUMBER_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_NUMBER)
    private String contractNumber;

    @Column(name = "contract_date")
    @NotNull(message = Messages.ERROR_CONTRACT_DATE_EMPTY)
    @JsonProperty
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_DATE)
    private LocalDate contractDate;

    @Column(name = "contract_status")
    @NotNull(message = Messages.ERROR_CONTRACT_STATUS_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_STATUS)
    private Integer contractStatus;

    @Column(name = "contract_balance")
    @NotNull(message = Messages.ERROR_CONTRACT_BALANCE_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT_BALANCE)
    private BigDecimal contractBalance;

    @JsonIgnore
    public Long getOrganizationId() {
        return organization.getId();
    }

    public String getOrganizationName() {
        return organization.getOrganizationName();
    }

    public String getOgrn() {
        return organization.getOgrn();
    }

    @JsonIgnore
    public String getHost() {
        return organization.getHost();
    }

    @JsonIgnore
    public Integer getPort() {
        return organization.getPort();
    }

    @JsonIgnore
    public Long getPersonId() {
        return person.getId();
    }

    public String getSnils() {
        return person.getSnils();
    }

    public String getFirstName() {
        return person.getFirstName();
    }

    public String getLastName() {
        return person.getLastName();
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public Integer getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(Integer contractStatus) {
        this.contractStatus = contractStatus;
    }

    public BigDecimal getContractBalance() {
        return contractBalance;
    }

    public void setContractBalance(BigDecimal contractBalance) {
        this.contractBalance = contractBalance;
    }

    public static Contract create(Long id) {
        if (id == null) {
            return null;
        }
        Contract contract = new Contract();
        contract.setId(id);
        return contract;
    }
}
