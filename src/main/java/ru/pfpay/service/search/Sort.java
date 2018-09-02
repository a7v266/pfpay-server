package ru.pfpay.service.search;

import ru.pfpay.config.Format;
import ru.pfpay.utils.StringJoiner;

import java.io.Serializable;

public class Sort implements Serializable {

    private String property;
    private Boolean desc;

    private Sort(String property) {
        this.property = property;
    }

    private Sort(String property, Boolean desc) {
        this.property = property;
        this.desc = desc;
    }

    @Override
    public String toString() {
        StringJoiner joiner = StringJoiner.on(Format.DOT, Format.OPEN_BRACE, Format.CLOSED_BRACE);
        joiner.add(property);
        joiner.add(desc);
        return joiner.toString();
    }

    public String getProperty() {
        return property;
    }

    public boolean isDesc() {
        return desc != null ? desc : false;
    }

    protected void setProperty(String property) {
        this.property = property;
    }

    public static Sort create(String property, Boolean desc) {
        return new Sort(property, desc);
    }

    public static Sort asc(String property) {
        return new Sort(property);
    }

    public static Sort desc(String property) {
        return new Sort(property, Boolean.TRUE);
    }
}
