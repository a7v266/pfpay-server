package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.config.Format;
import ru.pfpay.config.Token;
import ru.pfpay.domain.LoginParameters;
import ru.pfpay.domain.User;
import ru.pfpay.service.persistence.UserPersistence;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;
import ru.pfpay.utils.DigestUtils;

import java.time.LocalDateTime;

@Service
@Transactional(rollbackFor = Throwable.class)
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserPersistence userPersistence;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();

        if (securityContext != null &&
                securityContext.getAuthentication() != null &&
                securityContext.getAuthentication().getPrincipal() != null) {

            return (User) securityContext.getAuthentication().getPrincipal();
        }

        return null;
    }


    @Override
    public void login(LoginParameters login) {

        Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

        User user = (User) auth.getPrincipal();

        user.setTokenTime(LocalDateTime.now());
        user.setToken(DigestUtils.createSHA256(String.format("%s:%s", user.getUsername(), user.getTokenTime())));


        SecurityContextHolder.getContext().setAuthentication(auth);

        userPersistence.update(user);
    }

    @Override
    public void login(String token) {

        Authentication authentication = authenticationManager.authenticate(new Token(token));

        User user = (User) authentication.getPrincipal();

        user.setTokenTime(LocalDateTime.now());

        SecurityContextHolder.getContext().setAuthentication(authentication);

        userPersistence.update(user);
    }


    @Override
    public void logout(String token) {

        if (token == null || token.isEmpty()) {
            return;
        }

        Search search = new Search();

        search.addFilter(Filter.eq(User.TOKEN, token));

        User user = userPersistence.uniqueResult(search);

        if (user != null) {

            user.setToken(null);
            user.setTokenTime(null);

            userPersistence.update(user);

            SecurityContextHolder.clearContext();
        }
    }

    @Override
    public void authenticate(User user) {

        SecurityContextHolder.getContext().setAuthentication(new Token(user, Format.EMPTY_STRING));
    }

}
