package ru.pfpay.service;

import ru.pfpay.domain.UserGroup;
import ru.pfpay.service.search.Search;

import java.util.List;

public interface UserGroupService {

    List<UserGroup> getUserGroupList(Search search);

    Long getUserGroupCount(Search search);

    UserGroup getUserGroup(Long id);

    UserGroup mergeUserGroup(UserGroup userGroup);

}
