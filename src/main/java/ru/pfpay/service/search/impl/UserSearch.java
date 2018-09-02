package ru.pfpay.service.search.impl;

import ru.pfpay.domain.User;
import ru.pfpay.service.search.Filter;
import ru.pfpay.service.search.Search;
import ru.pfpay.utils.StringUtils;

public class UserSearch extends Search {

    public static final String SORT_USERNAME = User.USER_NAME;

    public void setQuest(String quest) {
        if (StringUtils.isNotEmpty(quest)) {
            addFilter(Filter.likeAnywhere(User.USER_NAME, quest));
        }
    }
}
