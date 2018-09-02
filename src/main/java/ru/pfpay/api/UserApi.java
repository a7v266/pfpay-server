package ru.pfpay.api;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.User;
import ru.pfpay.service.UserService;
import ru.pfpay.service.search.impl.UserSearch;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(name = Messages.API_USER, description = Messages.DESCRIPTION_USER_API)
public class UserApi extends CommonApi {

    private static final String HAS_ROLE_USER_VIEW = "hasRole('ROLE_USER_VIEW')";
    private static final String HAS_ROLE_USER_SAVE = "hasRole('ROLE_USER_SAVE')";
    private static final String HAS_ROLE_USER_GROUP_SAVE = "hasRole('ROLE_USER_GROUP_SAVE')";

    private static final String PATH_USER = "/user";
    private static final String PATH_USER_ID = "/user/{id}";

    @Autowired
    private UserService userService;

    @RequestMapping(value = PATH_USER, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_USER_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_USER_LIST)
    public List<User> getUserList(
            @RequestParam(required = false) @ApiQueryParam(name = LIMIT, required = false) Integer limit,
            @RequestParam(required = false) @ApiQueryParam(name = OFFSET, required = false) Integer offset) {

        UserSearch search = new UserSearch();

        search.setOffset(offset);
        search.setLimit(limit);

        return userService.getUserList(search);
    }


    @RequestMapping(value = PATH_USER_ID, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_USER_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_USER)
    public User getUser(
            @PathVariable Long id, HttpServletResponse response) {

        return userService.getUser(id);
    }

}
