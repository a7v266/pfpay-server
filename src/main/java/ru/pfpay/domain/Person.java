package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;
import ru.pfpay.json.BaseEntitySerializer;
import ru.pfpay.json.ContractDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "person", indexes = {
        @Index(columnList = "snils", unique = true)
})
@ApiObject(name = Messages.API_OBJECT_PERSON, description = Messages.DESCRIPTION_PERSON)
public class Person extends BaseEntityCustomId {

    public static final String SNILS = "snils";

    @ManyToOne
    @JoinColumn(name = "contract_id")
    @JsonProperty
    @JsonSerialize(using = BaseEntitySerializer.class)
    @JsonDeserialize(using = ContractDeserializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_CONTRACT, processtemplate = false)
    private Contract contract;

    @Column(name = "snils")
    @NotEmpty(message = Messages.ERROR_SNILS_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_SNILS)
    private String snils;

    @Column(name = "first_name")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_FIRST_NAME)
    private String firstName;

    @Column(name = "second_name")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_SECOND_NAME)
    private String secondName;

    @Column(name = "last_name")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_LAST_NAME)
    private String lastName;

    @JsonIgnore
    public Long getContractId() {
        return contract != null ? contract.getId() : null;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        this.contract = contract;
    }

    public String getSnils() {
        return snils;
    }

    public void setSnils(String snils) {
        this.snils = snils;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public static Person create(Long id) {
        if (id == null) {
            return null;
        }
        Person person = new Person();
        person.setId(id);
        return person;
    }
}
