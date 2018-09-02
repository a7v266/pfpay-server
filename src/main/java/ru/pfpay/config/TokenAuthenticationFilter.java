package ru.pfpay.config;

import org.springframework.context.ApplicationContext;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.pfpay.service.AuthenticationService;
import ru.pfpay.utils.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenAuthenticationFilter implements Filter {

    public static final String TOKEN_HEADER = "X-Auth-Token";

    private ApplicationContext applicationContext;

    public TokenAuthenticationFilter(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        SecurityContext context = SecurityContextHolder.getContext();

        if (context.getAuthentication() == null || !context.getAuthentication().isAuthenticated()) {

            String token = httpRequest.getHeader(TOKEN_HEADER);

            if (StringUtils.isEmpty(token)) {
                token = httpRequest.getParameter(TOKEN_HEADER);
            }

            if (StringUtils.isEmpty(token)) {
                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, Messages.ERROR_TOKEN_NOT_FOUND);
                return;
            }

            try {

                applicationContext.getBean(AuthenticationService.class).login(token);

                chain.doFilter(request, response);

            } catch (AuthenticationException exception) {

                httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, exception.getMessage());
            }
        }
    }

    @Override
    public void destroy() {
    }
}