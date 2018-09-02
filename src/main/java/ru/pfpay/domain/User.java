package ru.pfpay.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.pfpay.config.Messages;
import ru.pfpay.json.BaseEntitySerializer;
import ru.pfpay.json.UserGroupDeserializer;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Table(name = "local_user", indexes = {
        @Index(columnList = "username", unique = true),
        @Index(columnList = "token", unique = true)
})
@ApiObject(name = Messages.API_OBJECT_USER, description = Messages.DESCRIPTION_USER)
public class User extends BaseEntityCustomId implements UserDetails {

    public static final String USER_NAME = "username";
    public static final String USER_GROUP = "userGroup";
    public static final String PASSWORD = "password";
    public static final String TOKEN = "token";
    public static final String TOKEN_TIME = "tokenTime";

    @ManyToOne
    @JoinColumn(name = "user_group_id")
    @NotNull(message = Messages.ERROR_USER_GROUP_EMPTY)
    @JsonProperty
    @JsonSerialize(using = BaseEntitySerializer.class)
    @JsonDeserialize(using = UserGroupDeserializer.class)
    @ApiObjectField(description = Messages.DESCRIPTION_USER_GROUP)
    private UserGroup userGroup;

    @Column(name = "username")
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_USERNAME)
    private String username;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Transient
    @JsonProperty
    @ApiObjectField(description = Messages.DESCRIPTION_PASSWORD)
    private String rawPassword;

    @Column(name = "token")
    @JsonProperty
    private String token;

    @Column(name = "token_time")
    private LocalDateTime tokenTime;

    public User() {
    }

    public User(Long id, UserGroup userGroup) {
        super(id);
        this.userGroup = userGroup;
    }


    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userGroup.getUserRoles();
    }

    public String getGroupName() {
        return userGroup.getGroupName();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getTokenTime() {
        return tokenTime;
    }

    public void setTokenTime(LocalDateTime tokenTime) {
        this.tokenTime = tokenTime;
    }

    public Long getUserGroupId() {
        return userGroup.getId();
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }
}