package ru.pfpay.domain;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Length;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import ru.pfpay.config.Format;
import ru.pfpay.config.Messages;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "user_group")
@ApiObject(name = Messages.API_OBJECT_USER_GROUP, description = Messages.DESCRIPTION_USER_GROUP)
public class UserGroup extends BaseEntityCustomId {

    @Column(name = "group_name")
    @NotEmpty(message = Messages.ERROR_GROUP_NAME_EMPTY)
    @Length(max = Format.LENGTH_NAME, message = Messages.ERROR_GROUP_NAME_LENGTH)
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_GROUP_NAME)
    private String groupName;

    @Column(name = "user_roles")
    @Type(type = "ru.pfpay.hibernate.UserRoleSetType")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_USER_ROLES)
    private Set<UserRole> userRoles;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    public static UserGroup create(Long id) {
        if (id == null) {
            return null;
        }
        UserGroup userGroup = new UserGroup();
        userGroup.setId(id);
        return userGroup;
    }
}
