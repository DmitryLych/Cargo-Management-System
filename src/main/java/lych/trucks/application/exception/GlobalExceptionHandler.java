package lych.trucks.application.exception;

import lombok.extern.slf4j.Slf4j;
import org.dozer.MappingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.METHOD_NOT_ALLOWED;

/**
 * Exception handling controller.
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method for handling mapping exception.
     *
     * @param exception MappingException exception.
     * @return Exception message with UUID.
     */
    @ExceptionHandler(MappingException.class)
    public ResponseEntity mappingException(final MappingException exception) {

        final UUID errorUUID = UUID.randomUUID();

        log.error("Error-Id: {} - {}", errorUUID, exception.getMessage(), exception);

        final String content = exception.getMessage() + " Error-Id: " + errorUUID;

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(content);
    }

    /**
     * Method for handling no such method exception.
     *
     * @param exception HttpRequestMethodNotSupportedException exception.
     * @return Exception message with UUID.
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity noSuchMethodException(final HttpRequestMethodNotSupportedException exception) {

        final UUID errorUUID = UUID.randomUUID();

        log.error("Error-Id: {} - {}", errorUUID, exception.getMessage(), exception);

        final String content = "Incorrect method!!! Error message: " + exception.getMessage()
                + " Error-Id: " + errorUUID;

        return ResponseEntity.status(METHOD_NOT_ALLOWED).body(content);
    }

    /**
     * Method for handling empty result data access exception.
     *
     * @param exception EmptyResultDataAccessException exception.
     * @return Exception message with UUID.
     */
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity emptyResultDataAccessException(final EmptyResultDataAccessException exception) {

        final UUID errorUUID = UUID.randomUUID();

        log.error("Error-Id: {} - {}", errorUUID, exception.getMessage(), exception);

        final String content = "Object does not exist!!! Error-Id: " + errorUUID;

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(content);
    }

    /**
     * Method for handling.
     *
     * @param exception httpMessageNotReadableException exception.
     * @return Exception message with UUID.
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity httpMessageNotReadableException(final HttpMessageNotReadableException exception) {

        final UUID errorUUID = UUID.randomUUID();

        log.error("Error-Id: {} - {}", errorUUID, exception.getMessage(), exception);

        final String content = "Fields can not be empty!!! Error-Id: " + errorUUID;

        return ResponseEntity.status(BAD_REQUEST).body(content);
    }
}
