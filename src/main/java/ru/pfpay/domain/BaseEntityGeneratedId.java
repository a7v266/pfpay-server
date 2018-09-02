package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Messages;

import javax.persistence.*;

@MappedSuperclass
public abstract class BaseEntityGeneratedId extends BaseEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_ENTITY_ID)
    protected Long id;

    public BaseEntityGeneratedId() {
    }

    public BaseEntityGeneratedId(Long id) {
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
