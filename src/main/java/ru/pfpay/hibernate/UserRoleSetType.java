package ru.pfpay.hibernate;


import ru.pfpay.domain.UserRole;

public class UserRoleSetType extends SetType {

    @Override
    public Class returnedClass() {
        return UserRole.class;
    }
}
