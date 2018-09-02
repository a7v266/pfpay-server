package ru.pfpay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import ru.pfpay.domain.User;
import ru.pfpay.service.UserService;

import java.time.LocalDateTime;

@Service
public class TokenAuthenticationProvider implements AuthenticationProvider {

    public static final int TOKEN_TTL = 120;

    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String token = ((Token) authentication).getCredentials();

        User user = userService.loadUserByToken(token);

        if (user == null) {
            throw new BadCredentialsException(Messages.ERROR_USER_NOT_FOUND);
        }

        if (LocalDateTime.now().isAfter(user.getTokenTime().plusMinutes(TOKEN_TTL))) {
            throw new CredentialsExpiredException(Messages.ERROR_TOKEN_EXPIRED);
        }

        return new Token(user, token);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(Token.class);
    }
}
