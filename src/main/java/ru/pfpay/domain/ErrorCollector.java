package ru.pfpay.domain;


import ru.pfpay.config.Format;
import ru.pfpay.utils.CollectionUtils;
import ru.pfpay.utils.ObjectUtils;
import ru.pfpay.utils.StringJoiner;
import ru.pfpay.utils.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class ErrorCollector {

    private String title;

    private Collection<String> errors = new ArrayList<>();

    public ErrorCollector() {
    }

    public ErrorCollector(String title) {
        this.title = title;
    }

    public ErrorCollector(String title, Object... parameters) {
        this.title = StringUtils.format(title, parameters);
    }

    public ErrorCollector(String title, Collection<String> errors) {
        this.title = title;
        this.errors = errors;
    }

    public ErrorCollector(Collection<String> errors) {
        this.errors = errors;
    }

    public void add(Collection<String> errors) {
        if (errors == null || errors.isEmpty()) {
            return;
        }
        this.errors.addAll(errors);
    }

    public void add(ErrorCollector errorCollector) {
        if (errorCollector == null) {
            return;
        }
        if (errorCollector.hasErrors()) {
            errorCollector.getErrors().forEach(error -> errors.add(StringJoiner.on(Format.COLON_SPACE).skipEmpty().join(errorCollector.getTitle(), error)));
        }
    }

    public void add(Throwable throwable) {
        errors.add(throwable.getMessage());
    }

    public void add(String format, Object... values) {
        errors.add(StringUtils.format(format, values));
    }

    public Collection<String> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors.size() > 0;
    }

    public boolean isValid() {
        return errors.isEmpty();
    }

    public void assertNotNull(Object value, Supplier<String> supplier) {
        if (value == null) {
            errors.add(supplier.get());
        }
    }

    public void assertNotNull(Object value, String errorMessage, Object... parameters) {
        if (value == null) {
            add(errorMessage, parameters);
        }
    }

    public void assertNotEmpty(String value, Supplier<String> supplier) {
        if (value == null || value.isEmpty()) {
            add(supplier.get());
        }
    }

    public void assertNotEmpty(String value, String errorMessage, Object... parameters) {
        if (value == null || value.isEmpty()) {
            add(errorMessage, parameters);
        }
    }

    public void assertNotEmpty(Collection<?> collection, String errorMessage) {
        if (CollectionUtils.isEmpty(collection)) {
            add(errorMessage);
        }
    }

    public void assertOneItem(Collection<?> collection, String errorMessage) {
        if (CollectionUtils.isEmpty(collection)) {
            return;
        }
        if (collection.size() != 1) {
            add(errorMessage);
        }
    }

    public void assertMaxLength(String value, int length, Supplier<String> supplier) {
        if (value == null) {
            return;
        }
        if (value.length() <= length) {
            return;
        }
        errors.add(supplier.get());
    }

    public void assertMaxLength(String value, int length, String errorMessage) {
        if (value == null) {
            return;
        }
        if (value.length() <= length) {
            return;
        }
        errors.add(errorMessage);
    }

    public void assertPattern(String value, String pattern, Supplier<String> supplier) {
        if (value == null) {
            return;
        }
        if (value.matches(pattern)) {
            return;
        }
        errors.add(supplier.get());
    }

    public void assertPattern(String value, String pattern, String errorMessage, Object... parameters) {
        if (value == null) {
            return;
        }
        if (value.matches(pattern)) {
            return;
        }
        add(errorMessage, parameters);
    }

    public <T extends Comparable> void assertLess(T first, T second, String errorMessage, Object... parameters) {
        if (first == null || second == null) {
            return;
        }
        if (first.compareTo(second) < 0) {
            return;
        }
        add(errorMessage, parameters);
    }

    public <T extends Comparable> void assertLessOrEquals(T first, T second, String errorMessage, Object... parameters) {
        if (first == null || second == null) {
            return;
        }
        if (first.compareTo(second) <= 0) {
            return;
        }
        add(errorMessage, parameters);
    }

    public void assertTrue(boolean condition, String errorMessage, Object... parameters) {
        if (condition) {
            return;
        }
        add(errorMessage, parameters);
    }

    public <T> void assertEquals(T first, T second, String errorMessage, Object... parameters) {
        if (ObjectUtils.equals(first, second)) {
            return;
        }
        add(errorMessage, parameters);
    }

    public <T> void assertNotEquals(T first, T second, String errorMessage, Object... parameters) {
        if (ObjectUtils.notEquals(first, second)) {
            return;
        }
        add(errorMessage, parameters);
    }

    public void assertNull(Object object, String errorMessage) {
        if (object == null) {
            return;
        }
        add(errorMessage);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTitle(String format, Object... parameters) {
        this.title = String.format(format, parameters);
    }

    public void throwException() {
        if (hasErrors()) {
            throw new ErrorCollectorException(this);
        }
    }

    public void throwException(Consumer<ErrorCollector> consumer) {
        if (hasErrors()) {
            consumer.accept(this);
            throw new ErrorCollectorException(this);
        }
    }

    public String createErrorMessage() {
        if (errors.size() == 0) {
            return null;
        }
        return StringJoiner.on(Format.COLON_SPACE).skipEmpty().join(title, StringJoiner.on(Format.COMMA_SPACE).join(errors));
    }
}
