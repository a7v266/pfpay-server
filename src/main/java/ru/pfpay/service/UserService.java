package ru.pfpay.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.pfpay.domain.User;
import ru.pfpay.service.search.Search;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUserList(Search search);

    User getUser(Long id);

    Long getUserCount(Search search);

    User mergeUser(User user);

    User loadUserByToken(String token);
}
