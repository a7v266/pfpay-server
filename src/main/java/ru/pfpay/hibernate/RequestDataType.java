package ru.pfpay.hibernate;

import ru.pfpay.domain.RequestData;

public class RequestDataType extends JsonType {

    @Override
    public Class returnedClass() {
        return RequestData.class;
    }
}
