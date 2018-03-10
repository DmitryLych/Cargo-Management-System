package lych.trucks.application.exception;

import lombok.extern.slf4j.Slf4j;
import lych.trucks.domain.exception.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

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
     * Method for handling exception.
     *
     * @param exception a exception.
     * @return Exception message with UUID.
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity handleAnException(final Exception exception) {

        final UUID errorUUID = UUID.randomUUID();

        log.error("Error-ID: {} - {}", errorUUID, exception.getMessage(), exception);

        final String message = "An error occurred. Please contact support. Error-ID:" + errorUUID;

        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ExceptionResponse.anExceptionResponse(message, errorUUID.toString()));

    }

    /**
     * Method for handling {@link IllegalArgumentException} and {@link IllegalStateException}.
     *
     * @param exception a exception.
     * @return exception message with UUID.
     */
    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class,
            AccessDeniedException.class, UsernameNotFoundException.class})
    @ResponseBody
    public ResponseEntity illegalArgumentOrStateException(final RuntimeException exception) {

        final UUID errorUUID = UUID.randomUUID();

        final String exceptionMessage = exception.getMessage();

        log.error("Error-Id: {} - {}", errorUUID, exceptionMessage, exception);

        return ResponseEntity.status(BAD_REQUEST).body(ExceptionResponse
                .anExceptionResponse(exceptionMessage, errorUUID.toString()));
    }
}
