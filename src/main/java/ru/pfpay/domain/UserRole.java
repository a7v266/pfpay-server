package ru.pfpay.domain;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {

    ROLE_USER_VIEW,
    ROLE_USER_SAVE,
    ROLE_USER_DELETE,

    ROLE_USER_GROUP_VIEW,
    ROLE_USER_GROUP_SAVE,
    ROLE_USER_GROUP_DELETE,

    ROLE_ORGANIZATION_VIEW,
    ROLE_ORGANIZATION_SAVE,
    ROLE_ORGANIZATION_DELETE,

    ROLE_PERSON_VIEW,
    ROLE_PERSON_SAVE,
    ROLE_PERSON_DELETE,

    ROLE_CONTRACT_VIEW,
    ROLE_CONTRACT_SAVE,
    ROLE_CONTRACT_DELETE,

    ROLE_REQUEST_VIEW,
    ROLE_REQUEST_SAVE,
    ROLE_REQUEST_DELETE,
    ROLE_REQUEST_EXECUTE,

    ROLE_CONFIG_VIEW,
    ROLE_CONFIG_SAVE,
    ROLE_CONFIG_DELETE;

    @Override
    public String getAuthority() {
        return name();
    }
}
