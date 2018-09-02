package ru.pfpay.hibernate;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public abstract class ListType extends JsonType {

    @Override
    public JavaType createJavaType(ObjectMapper mapper) {
        return mapper.getTypeFactory().constructCollectionType(List.class, returnedClass());
    }
}