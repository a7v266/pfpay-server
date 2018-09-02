package ru.pfpay.utils;


import ru.pfpay.config.Format;

import java.util.Collection;

public class StringJoiner {

    private String FORMAT = "%s%s%s";

    private boolean skipEmpty = false;

    private String separator = Format.EMPTY_STRING;

    private String prefix = Format.EMPTY_STRING;

    private String suffix = Format.EMPTY_STRING;

    private StringBuilder builder = new StringBuilder();

    public StringJoiner() {
    }

    public StringJoiner(String separator) {
        this.separator = separator;
    }

    public StringJoiner(String separator, String prefix, String suffix) {
        this.separator = separator;
        this.prefix = prefix;
        this.suffix = suffix;
    }

    @Override
    public String toString() {
        return StringUtils.format(FORMAT, prefix, builder.toString(), suffix);
    }

    public StringJoiner skipEmpty() {
        skipEmpty = true;
        return this;
    }

    public String join(String... items) {

        if (ArrayUtils.isNotEmpty(items)) {
            for (String item : items) {
                add(item);
            }
        }

        return toString();
    }

    public String join(Collection<String> items) {

        if (CollectionUtils.isNotEmpty(items)) {
            items.forEach(this::add);
        }

        return toString();
    }

    public String join(String head, Collection<String> items) {

        add(head);

        if (CollectionUtils.isNotEmpty(items)) {
            items.forEach(this::add);
        }

        return toString();
    }


    public StringJoiner add(Object item) {

        return add(StringUtils.valueOf(item));
    }

    public StringJoiner add(Collection<?> collection) {

        if (CollectionUtils.isNotEmpty(collection)) {
            collection.forEach(this::add);
        }

        return this;
    }

    public StringJoiner add(Object[] array) {

        if (ArrayUtils.isNotEmpty(array)) {
            for (Object object : array) {
                add(object);
            }
        }
        return this;
    }

    public StringJoiner add(String item) {

        if (skipEmpty && StringUtils.isEmpty(item)) {
            return this;
        }

        if (builder.length() > 0) {
            builder.append(separator);
        }

        builder.append(item);

        return this;
    }

    public static StringJoiner on() {
        return new StringJoiner();
    }

    public static StringJoiner on(String separator) {
        return new StringJoiner(separator);
    }

    public static StringJoiner on(String separator, String prefix, String suffix) {
        return new StringJoiner(separator, prefix, suffix);
    }
}
