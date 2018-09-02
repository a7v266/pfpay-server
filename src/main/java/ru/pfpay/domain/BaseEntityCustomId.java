package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntityCustomId extends BaseEntity {

    public static final String ID = "id";

    @Id
    @Column(name = "id")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_ENTITY_ID)
    private Long id;

    public BaseEntityCustomId() {
    }

    public BaseEntityCustomId(Long id) {
        this.id = id;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
