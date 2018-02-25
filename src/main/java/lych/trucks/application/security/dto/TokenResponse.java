package lych.trucks.application.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationServiceException;

import java.io.Serializable;

import static lombok.AccessLevel.PRIVATE;

/**
 * Token dto.
 */
@Getter
@RequiredArgsConstructor(access = PRIVATE)
@JsonTypeName(value = "token")
public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 8203806666374009154L;

    @JsonProperty
    private final String token;

    /**
     * Create token with parameters.
     *
     * @param token a token.
     * @return created token.
     */
    public static TokenResponse aTokenResponse(final String token) {

        if (token == null) {
            throw new AuthenticationServiceException("No Authentication.");
        }

        return new TokenResponse(token);
    }
}
