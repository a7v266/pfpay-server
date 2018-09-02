package ru.pfpay.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommonApi {

    static final String PERSON_ID = "personId";
    static final String CONTRACT_ID = "contractId";
    static final String ORGANIZATION_ID = "organizationId";

    static final String AGENT_ID = "agentId";
    static final String BACKUP_FILE = "backupFile";
    static final String BILL_ID = "billId";
    static final String BILL_IDS = "billIds";
    static final String BILL_STATUS = "billStatus";
    static final String BILLING_IDS = "billingIds";
    static final String BILLING_MONTH = "billingMonth";
    static final String BILLING_STATUS = "billingStatus";
    static final String BILLING_YEAR = "billingYear";
    static final String CATALOG_ID = "catalogId";
    static final String CLIENT_FILE = "clientFile";
    static final String CLIENT_ID = "clientId";
    static final String CLIENT_NAME = "clientName";
    static final String CLIENT_TYPE = "clientType";
    static final String CONTRACT_FILE = "contractFile";
    static final String CONTRACT_NUMBER = "contractNumber";
    static final String CONTRACT_STATUS = "contractStatus";
    static final String CONTRACT_TYPE = "contractType";
    static final String CURRENT_VALUE = "currentValue";
    static final String DESC = "desc";
    static final String DETAIL_TYPE = "detailType";
    static final String EMPLOYEE_ID = "employeeId";
    static final String END_DATE = "endDate";
    static final String ENTITY_ID = "entityId";
    static final String ENTITY_TYPE = "entityType";
    static final String EVENT_TYPE = "eventType";
    static final String EXTERNAL = "external";
    static final String FREE = "free";
    static final String GROUP_DIGEST = "groupDigest";
    static final String GROUP_ID = "groupId";
    static final String GROUPING = "grouping";
    static final String HUB_ID = "hubId";
    static final String ID = "id";
    static final String IDS = "ids";
    static final String LIMIT = "limit";
    static final String LIMIT_TYPE = "limitType";
    static final String MIGRATION_STATUS = "migrationStatus";
    static final String MIGRATION_TYPE = "migrationType";
    static final String MONTH = "month";
    static final String NAME = "name";
    static final String NODE_BRANCH = "nodeBranch";
    static final String NODE_ID = "nodeId";
    static final String NODE_IDS = "nodeIds";
    static final String NODE_LOCATION = "nodeLocation";
    static final String NODE_NAME = "nodeName";
    static final String NOT_CLIENT_ID = "notClientId";
    static final String NOT_CONTRACT_ID = "notContractId";
    static final String OFFSET = "offset";
    static final String OPERATOR_ID = "operatorId";
    static final String PAYMENT_FILE = "paymentFile";
    static final String PAYMENT_MONTH = "paymentMonth";
    static final String PAYMENT_NUMBER = "paymentNumber";
    static final String PAYMENT_SOURCE = "paymentSource";
    static final String PAYMENT_STATUS = "paymentStatus";
    static final String PAYMENT_YEAR = "paymentYear";
    static final String PREVIOUS_VALUE = "previousValue";
    static final String PRODUCT_ID = "productId";
    static final String PRODUCT_IDS = "productIds";
    static final String PRODUCT_TYPE = "productType";
    static final String PROVIDER_ID = "providerId";
    static final String QUEST = "quest";
    static final String REQUEST_STATUS = "requestStatus";
    static final String REQUEST_TYPE = "requestType";
    static final String RESOURCE_TYPE = "resourceType";
    static final String SERVICE_REASON = "serviceReason";
    static final String SORT = "sort";
    static final String SORTS = "sorts";
    static final String START_DATE = "startDate";
    static final String TARIFF_END_DATE = "tariffEndDate";
    static final String TARIFF_NAME = "tariffName";
    static final String TARIFF_START_DATE = "tariffStartDate";
    static final String TRAFFIC_END_DATE = "trafficEndDate";
    static final String TRAFFIC_START_DATE = "trafficStartDate";
    static final String UNIVERSAL_IDENTIFIER = "universalIdentifier";
    static final String USER_ID = "userId";
    static final String YEAR = "year";
    static final String PRODUCT_NAME = "productName";
    static final String AGENT_NAME = "agentName";
    static final String IP_ADDRESS = "ipAddress";
    static final String PHONE_NUMBER = "phoneNumber";
    static final String NODE_NAMES = "nodeNames";
    static final String SHOW_EMPTY = "showEmpty";
    static final String HIDE_EMPTY = "hideEmpty";
    static final String SHOW_CLOSED = "showClosed";
    static final String HIDE_CONTRACTED = "hideContracted";
    static final String USERNAME = "username";
    static final String FILE_NAME = "fileName";
    static final String HAS_ERRORS = "hasErrors";
    static final String EXCLUDED_IDS = "excludedIds";
    static final String TRAFFIC_FILE = "trafficFile";

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonApi.class);

    <T> T sendError(HttpServletResponse response, int errorCode, String errorMessage, Object... parameters) {
        try {
            response.sendError(errorCode, String.format(errorMessage, parameters));

        } catch (IOException exception) {

            LOGGER.error(errorMessage, exception);
        }

        return null;
    }

    <T> T sendNotFoundError(HttpServletResponse response, String errorMessage, Object... parameters) {
        return sendError(response, HttpServletResponse.SC_NOT_FOUND, errorMessage, parameters);
    }
}
