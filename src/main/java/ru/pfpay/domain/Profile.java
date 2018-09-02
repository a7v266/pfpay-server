package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Profile {

    @JsonProperty
    private String username;

    @JsonProperty
    private String token;

    @JsonProperty
    private String organizationName;

    @JsonProperty
    private String ogrn;

    @JsonProperty
    private String host;

    @JsonProperty
    private Integer port;

    @JsonProperty
    private String remoteHost;

    @JsonProperty
    private Integer remotePort;

    public void setCurrentUser(User user) {
        username = user.getUsername();
        token = user.getToken();
    }

    public void setCurrentOrganization(Organization organization) {
        organizationName = organization.getOrganizationName();
        ogrn = organization.getOgrn();
        host = organization.getHost();
        port = organization.getPort();
    }

    public void setRemoteOrganization(Organization organization) {
        remoteHost = organization.getHost();
        remotePort = organization.getPort();
    }
}
