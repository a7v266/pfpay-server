package ru.pfpay.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ObjectList {

    @JsonProperty
    private Boolean success = Boolean.TRUE;

    @JsonProperty
    private List<?> items;

    @JsonProperty
    private Long totalCount;

    public static ObjectList create(List<?> items, Long totalCount) {

        ObjectList objectList = new ObjectList();
        objectList.items = items;
        objectList.totalCount = totalCount;
        return objectList;
    }
}
