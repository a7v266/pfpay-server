package ru.pfpay.api;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.pfpay.config.Messages;
import ru.pfpay.config.TokenAuthenticationFilter;
import ru.pfpay.domain.LoginParameters;
import ru.pfpay.domain.Profile;
import ru.pfpay.service.AuthenticationService;
import ru.pfpay.service.ConfigService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@RestController
@Api(name = Messages.API_AUTHENTICATION, description = Messages.DESCRIPTION_AUTHENTICATION_API)
public class AuthenticationApi extends CommonApi {

    private static final String PATH_LOGIN = "/login";
    private static final String PATH_LOGOUT = "/logout";

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private ConfigService configService;

    @RequestMapping(value = PATH_LOGIN, method = RequestMethod.POST)
    @ApiMethod(description = Messages.DESCRIPTION_LOGIN)
    public Profile login(@Valid @RequestBody LoginParameters loginParameters, HttpServletResponse response) {

        authenticationService.login(loginParameters);

        Profile profile = new Profile();

        profile.setCurrentUser(authenticationService.getCurrentUser());
        profile.setCurrentOrganization(configService.getCurrentOrganization());
        profile.setRemoteOrganization(configService.getRemoteOrganization());

        return profile;
    }

    @RequestMapping(value = PATH_LOGOUT, method = RequestMethod.POST)
    @ApiMethod(description = Messages.DESCRIPTION_LOGOUT)
    public void logout(HttpServletRequest request) {

        String token = request.getHeader(TokenAuthenticationFilter.TOKEN_HEADER);

        authenticationService.logout(token);
    }

}
