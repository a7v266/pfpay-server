package ru.pfpay.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.pfpay.config.Loggers;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.*;
import ru.pfpay.utils.FileUtils;
import ru.pfpay.utils.ObjectUtils;
import ru.pfpay.utils.StringUtils;

import javax.annotation.PostConstruct;
import java.io.*;


@Service
public class ConfigServiceImpl implements ConfigService {

    @Value("${config.path}")
    private String configPath;

    @Value("${currentOrganization.id}")
    private Long currentOrganizationId;

    @Value("${remoteOrganization.id}")
    private Long remoteOrganizationId;

    @Autowired
    private ResourceLoader resourceLoader;

    private static final String CONFIG_PATH_FORMAT = "%s/%s";
    private static final String RESOURCE_PATH_FORMAT = "classpath:config/%s";

    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private PersonService personService;

    @Autowired
    private ContractService contractService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String FILE_USER = "user.json";
    private static final String FILE_USER_GROUP = "user-group.json";
    private static final String FILE_ORGANIZATION = "organization.json";
    private static final String FILE_PERSON = "person.json";
    private static final String FILE_CONTRACT = "contract.json";

    private static final String SECURITY_ALGORITHM = "DSA";
    private static final String SECURITY_PROVIDER = "SUN";


    @PostConstruct
    public void init() throws IOException {

        loadUserGroup();

        loadUser();

        loadOrganization();

        loadPerson();

        loadContract();

        createTestRequests();
    }

    @Override
    public Organization getCurrentOrganization() {

        return organizationService.getOrganization(currentOrganizationId, id -> {
            throw new ErrorCollectorException(Messages.ERROR_CURRENT_ORGANIZATION_EMPTY);
        });
    }

    @Override
    public Organization getRemoteOrganization() {
        return organizationService.getOrganization(remoteOrganizationId, id -> {
            throw new ErrorCollectorException(Messages.ERROR_REMOTE_ORGANIZATION_EMPTY);
        });
    }

    @Override
    public String getLocalHost() {
        return getCurrentOrganization().getHost();
    }

    @Override
    public Integer getLocalPort() {
        return getCurrentOrganization().getPort();
    }

    @Override
    public String getRemoteHost() {
        return getRemoteOrganization().getHost();
    }

    @Override
    public Integer getRemotePort() {
        return getRemoteOrganization().getPort();
    }

    @Override
    public boolean isRegulator() {
        return ObjectUtils.equals(currentOrganizationId, remoteOrganizationId);
    }

    private void loadOrganization() throws IOException {
        Organization[] organizations = load(Organization[].class, getInputStream(FILE_ORGANIZATION));
        for (Organization organization : organizations) {
            organizationService.saveOrganization(organization);
        }
    }

    private void loadUserGroup() throws IOException {
        UserGroup[] userGroups = load(UserGroup[].class, getInputStream(FILE_USER_GROUP));
        for (UserGroup userGroup : userGroups) {
            userGroupService.mergeUserGroup(userGroup);
        }
    }

    private void loadUser() throws IOException {
        User[] users = load(User[].class, getInputStream(FILE_USER));
        for (User user : users) {
            userService.mergeUser(user);
        }
    }

    private void loadPerson() throws IOException {
        Person[] persons = load(Person[].class, getInputStream(FILE_PERSON));
        for (Person person : persons) {
            personService.mergePerson(person);
        }
    }

    private void loadContract() throws IOException {

        if (contractService.isExist()) {
            return;
        }

        Contract[] contracts = load(Contract[].class, getInputStream(FILE_CONTRACT));

        for (Contract contract : contracts) {

            contractService.mergeContract(contract);

            Person person = contract.getPerson();

            person.setContract(contract);

            personService.mergePerson(person);
        }
    }

    private <T> T load(Class<T> clazz, InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            return objectMapper.readValue(reader, clazz);
        }
    }

    private InputStream getInputStream(String fileName) throws IOException {

        String filePath = StringUtils.format(CONFIG_PATH_FORMAT, configPath, fileName);

        if (FileUtils.exist(filePath)) {

            Loggers.CONFIG_LOGGER.info("Load config file {}", filePath);

            return new FileInputStream(filePath);
        }

        String resourcePath = StringUtils.format(RESOURCE_PATH_FORMAT, fileName);

        Loggers.CONFIG_LOGGER.info("Load default resource {}", resourcePath);

        return resourceLoader.getResource(resourcePath).getInputStream();
    }


    private void createTestRequests() {

        for (int index = 1; index < 1000; index++) {

            Request request = new Request();

            request.setId((long) index);
            request.setRequestDirection(RequestDirection.OUTGOING);
            request.setRequestStatus(RequestStatus.CREATED);
            request.setRequestType(0);

            requestService.saveRequest(request);
        }
    }
}