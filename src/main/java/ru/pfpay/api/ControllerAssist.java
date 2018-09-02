package ru.pfpay.api;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.WebRequest;
import ru.pfpay.config.Messages;
import ru.pfpay.domain.ErrorCollector;
import ru.pfpay.domain.ErrorCollectorException;

@ControllerAdvice
public class ControllerAssist {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.initDirectFieldAccess();
    }

    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(exception, headers, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ErrorCollectorException.class})
    public ResponseEntity<Object> handleErrorCollectorException(ErrorCollectorException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(exception.getErrorCollector(), headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<Object> handleJsonMappingException(HttpMessageNotReadableException exception, WebRequest request) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (exception.getCause() instanceof InvalidFormatException) {

            return new ResponseEntity<>(createErrorCollector((InvalidFormatException) exception.getCause()), headers, HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(exception, headers, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    private ErrorCollector createErrorCollector(InvalidFormatException exception) {

        return new ErrorCollector(Messages.ERROR_PARSE_VALUE_FORMAT, exception.getValue());
    }

}
