package ru.pfpay.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.pfpay.domain.User;

import java.util.Collection;
import java.util.Collections;


public class Token implements Authentication {

    private User principal;

    private String credentials;

    private boolean authenticated;

    public Token(String credentials) {
        this.credentials = credentials;
        this.authenticated = false;
    }

    public Token(User principal, String credentials) {
        this.principal = principal;
        this.credentials = credentials;
        this.authenticated = true;
    }

    @Override
    public User getPrincipal() {
        return principal;
    }

    @Override
    public String getCredentials() {
        return credentials;
    }

    @Override
    public String getName() {
        return principal != null ? principal.getUsername() : null;
    }

    @Override
    public Object getDetails() {
        return principal != null ? principal.getUserGroup() : null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return principal != null ? principal.getAuthorities() : Collections.EMPTY_SET;
    }

    @Override
    public boolean isAuthenticated() {
        return authenticated;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }
}
