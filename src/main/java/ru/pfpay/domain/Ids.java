package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Collection;


@ApiObject(name = Messages.API_OBJECT_IDS, description = Messages.DESCRIPTION_IDS)
public class Ids implements Serializable {

    @JsonProperty
    @NotEmpty(message = Messages.ERROR_IDS_EMPTY)
    @ApiObjectField(description = Messages.DESCRIPTION_IDS)
    private Collection<Long> ids;

    public Collection<Long> getIds() {
        return ids;
    }
}