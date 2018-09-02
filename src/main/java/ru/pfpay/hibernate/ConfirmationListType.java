package ru.pfpay.hibernate;

import ru.pfpay.domain.Confirmation;

public class ConfirmationListType extends ListType {

    @Override
    public Class returnedClass() {
        return Confirmation.class;
    }
}
