package ru.pfpay.api;

import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.Person;
import ru.pfpay.service.PersonService;
import ru.pfpay.service.search.impl.PersonSearch;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@Api(name = Messages.API_PERSON, description = Messages.DESCRIPTION_PERSON_API)
public class PersonApi extends CommonApi {

    private static final String HAS_ROLE_PERSON_VIEW = "hasRole('ROLE_PERSON_VIEW')";
    private static final String HAS_ROLE_PERSON_SAVE = "hasRole('ROLE_PERSON_SAVE')";
    private static final String HAS_ROLE_PERSON_DELETE = "hasRole('ROLE_PERSON_DELETE')";

    private static final String PATH_PERSON = "/person";
    private static final String PATH_PERSON_ID = "/person/{id}";
    private static final String PATH_PERSON_COUNT = "/person/count";

    @Autowired
    private PersonService personService;


    @RequestMapping(value = PATH_PERSON, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_PERSON_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_PERSON_LIST)
    public List<Person> getPersonList(
            @RequestParam(required = false) @ApiQueryParam(name = LIMIT, required = false) Integer limit,
            @RequestParam(required = false) @ApiQueryParam(name = OFFSET, required = false) Integer offset) {

        PersonSearch search = new PersonSearch();

        search.setOffset(offset);
        search.setLimit(limit);

        return personService.getPersonList(search);
    }

    @RequestMapping(value = PATH_PERSON_ID, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_PERSON_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_PERSON)
    public Person getPerson(
            @PathVariable @ApiPathParam(name = ID) Long id,
            HttpServletResponse response) {

        return personService.getPerson(id);
    }


    @RequestMapping(value = PATH_PERSON_COUNT, method = RequestMethod.GET)
    @PreAuthorize(HAS_ROLE_PERSON_VIEW)
    @ApiMethod(description = Messages.DESCRIPTION_GET_PERSON_COUNT)
    public Long getPersonCount() {

        PersonSearch search = new PersonSearch();

        return personService.getPersonCount(search);
    }
}
