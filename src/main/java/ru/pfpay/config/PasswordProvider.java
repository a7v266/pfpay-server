package ru.pfpay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.stereotype.Service;
import ru.pfpay.service.UserService;

import javax.annotation.PostConstruct;

@Service
public class PasswordProvider extends DaoAuthenticationProvider {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {
        setUserDetailsService(userService);
    }
}
