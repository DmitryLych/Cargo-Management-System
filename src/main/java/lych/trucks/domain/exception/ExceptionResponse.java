package lych.trucks.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Error Response.
 */
@Getter
@RequiredArgsConstructor(staticName = "anExceptionResponse")
public final class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = -5254090582733859823L;

    private final String message;

    private final String errorId;
}
