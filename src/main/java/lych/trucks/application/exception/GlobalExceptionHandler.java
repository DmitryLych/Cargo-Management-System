package lych.trucks.application.exception;

import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.exception.ExceptionResponse;
import org.dozer.MappingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

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

        final String exceptionMessage = exception.getMessage();

        log.error("Error-Id: {} - {}", errorUUID, exceptionMessage, exception);

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(ExceptionResponse
                .anExceptionResponse(exceptionMessage, errorUUID.toString()));
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public ResponseEntity illegalArgumentOrStateException(final RuntimeException exception) {

        final UUID errorUUID = UUID.randomUUID();

        final String exceptionMessage = exception.getMessage();

        log.error("Error-Id: {} - {}", errorUUID, exceptionMessage, exception);

        return ResponseEntity.status(BAD_REQUEST).body(ExceptionResponse
                .anExceptionResponse(exceptionMessage, errorUUID.toString()));
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
}
