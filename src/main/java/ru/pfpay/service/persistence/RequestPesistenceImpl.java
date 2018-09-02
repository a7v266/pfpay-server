package ru.pfpay.service.persistence;

import org.springframework.stereotype.Repository;
import ru.pfpay.domain.Request;


@Repository
public class RequestPesistenceImpl extends BasePersistenceImpl<Request, Long> implements RequestPersistence {

    public RequestPesistenceImpl() {
        super(Request.class);
    }
}
