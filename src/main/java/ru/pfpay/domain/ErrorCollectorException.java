package ru.pfpay.domain;


import ru.pfpay.config.Format;
import ru.pfpay.config.Messages;
import ru.pfpay.utils.StringJoiner;

public class ErrorCollectorException extends RuntimeException {

    private ErrorCollector errorCollector;

    public ErrorCollectorException(String errorMessage, Object... parameters) {
        errorCollector = new ErrorCollector(Messages.ERROR);
        errorCollector.add(errorMessage, parameters);
    }

    public ErrorCollectorException(ErrorCollector errorCollector) {
        this.errorCollector = errorCollector;
    }

    public ErrorCollector getErrorCollector() {
        return errorCollector;
    }

    public String getMessage() {

        return StringJoiner.on(Format.COLON_SPACE).skipEmpty().join(errorCollector.getTitle(), StringJoiner.on(Format.COMMA_SPACE).skipEmpty().join(errorCollector.getErrors()));
    }
}
