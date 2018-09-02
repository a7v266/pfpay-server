package ru.pfpay.service;


import ru.pfpay.domain.LoginParameters;
import ru.pfpay.domain.User;

public interface AuthenticationService {

    void login(LoginParameters login);

    void login(String token);

    void logout(String token);

    User getCurrentUser();

    void authenticate(User user);
}
