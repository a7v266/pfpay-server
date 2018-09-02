package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.User;


@Repository
public class UserPersistenceImpl extends BasePersistenceImpl<User, Long> implements UserPersistence {

    public UserPersistenceImpl() {
        super(User.class);
    }
}
