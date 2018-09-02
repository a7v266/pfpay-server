package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Format;
import ru.pfpay.config.Messages;

import javax.validation.constraints.NotEmpty;


@ApiObject(name = Messages.API_OBJECT_LOGIN_PARAMETERS, description = Messages.DESCRIPTION_LOGIN_PARAMETERS)
public class LoginParameters {

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_USER_NAME_EMPTY)
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_USER_NAME_LENGTH)
    @ApiObjectField(description = Messages.DESCRIPTION_USERNAME)
    private String username;

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_PASSWORD_EMPTY)
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_PASSWORD_LENGTH)
    @ApiObjectField(description = Messages.DESCRIPTION_PASSWORD)
    private String password;


    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
