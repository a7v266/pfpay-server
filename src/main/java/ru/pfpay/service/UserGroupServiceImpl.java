package ru.pfpay.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pfpay.domain.UserGroup;
import ru.pfpay.service.persistence.UserGroupPersistence;
import ru.pfpay.service.search.Search;

import java.util.List;

@Service
@Transactional(rollbackFor = Throwable.class)
public class UserGroupServiceImpl implements UserGroupService {

    @Autowired
    private UserGroupPersistence userGroupPersistence;

    @Override
    public List<UserGroup> getUserGroupList(Search search) {
        return userGroupPersistence.list(search);
    }

    @Override
    public Long getUserGroupCount(Search search) {
        return userGroupPersistence.count(search);
    }

    @Override
    public UserGroup getUserGroup(Long id) {
        return userGroupPersistence.get(id);
    }

    @Override
    public UserGroup mergeUserGroup(UserGroup userGroup) {
        return userGroupPersistence.merge(userGroup);
    }
}
