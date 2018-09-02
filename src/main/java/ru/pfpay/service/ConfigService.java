package ru.pfpay.service;

import ru.pfpay.domain.Organization;

public interface ConfigService {

    Organization getCurrentOrganization();

    Organization getRemoteOrganization();

    String getLocalHost();

    Integer getLocalPort();

    String getRemoteHost();

    Integer getRemotePort();

    boolean isRegulator();
}
