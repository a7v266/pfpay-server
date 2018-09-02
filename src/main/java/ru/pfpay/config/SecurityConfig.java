package ru.pfpay.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.pfpay.service.UserService;


@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenAuthenticationProvider tokenAuthenticationProvider;


    private static final String PATH_LOGIN = "/login";
    private static final String PATH_LOGOUT = "/logout";
    private static final String PATH_JSON_DOC = "/jsondoc*";

    @Autowired
    public void configureAuthenticationManagerBuilder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userService);
        builder.authenticationProvider(tokenAuthenticationProvider);
    }

    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        AuthenticationManager authenticationManager = super.authenticationManagerBean();

        return authenticationManager;
    }

    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/jsondoc*");
        web.ignoring().antMatchers("/webjars/**");
        web.ignoring().antMatchers("/login");
        web.ignoring().antMatchers(HttpMethod.OPTIONS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.formLogin().disable();
        http.logout().disable();

        http.authorizeRequests().antMatchers(HttpMethod.OPTIONS).permitAll();
        http.authorizeRequests().antMatchers("/login").permitAll();
        http.authorizeRequests().anyRequest().authenticated();

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(new TokenAuthenticationFilter(applicationContext), UsernamePasswordAuthenticationFilter.class);
    }
}
