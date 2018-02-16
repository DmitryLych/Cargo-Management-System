package lych.trucks.domain.http;

import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class HttpStatusCode {

    /**
     * Status code 200 (OK).
     */
    public static final int OK = 200;

    /**
     * Status code 201 (CREATED).
     */
    public static final int CREATED = 201;

    /**
     * Status code 400 (BAD_REQUEST).
     */
    public static final int BAD_REQUEST = 400;

    /**
     * Status code 500 (INTERNAL_SERVER_ERROR).
     */
    public static final int INTERNAL_SERVER_ERROR = 500;
}
