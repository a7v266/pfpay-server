package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.Length;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Format;
import ru.pfpay.config.Messages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "organization", indexes = {
        @Index(columnList = "ogrn", unique = true)
})
@ApiObject(name = Messages.API_OBJECT_ORGANIZATION, description = Messages.DESCRIPTION_ORGANIZATION)
public class Organization extends BaseEntityCustomId {

    public static final String OGRN = "ogrn";

    @Column(name = "organization_name")
    @NotEmpty(message = Messages.ERROR_ORGANIZATION_NAME_EMPTY)
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_ORGANIZATION_NAME_LENGTH)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_ORGANIZATION_NAME)
    private String organizationName;

    @Column(name = "ogrn", length = 13)
    @NotEmpty(message = Messages.ERROR_ORGN_EMPTY)
    @Pattern(regexp = Format.PATTERN_OGRN, message = Messages.ERROR_OGRN_INVALID)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_OGRN)
    private String ogrn;

    @Column(name = "host", length = 15)
    @NotEmpty(message = Messages.ERROR_HOST_EMPTY)
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_HOST_LENGTH)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_HOST)
    private String host;

    @Column(name = "port")
    @NotNull(message = Messages.ERROR_PORT_EMPTY)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_PORT)
    private Integer port;

    public String getOgrn() {
        return ogrn;
    }

    public void setOgrn(String ogrn) {
        this.ogrn = ogrn;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public static Organization create(Long id) {
        if (id == null) {
            return null;
        }
        Organization organization = new Organization();
        organization.setId(id);
        return organization;
    }
}
