package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.UserGroup;


@Repository
public class UserGroupPersistenceImpl extends BasePersistenceImpl<UserGroup, Long> implements UserGroupPersistence {

    public UserGroupPersistenceImpl() {
        super(UserGroup.class);
    }
}
