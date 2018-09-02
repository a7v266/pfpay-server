package ru.pfpay.hibernate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Set;

public abstract class SetType extends JsonType {

    @Override
    public JavaType createJavaType(ObjectMapper mapper) {
        return mapper.getTypeFactory().constructCollectionType(Set.class, returnedClass());
    }
}