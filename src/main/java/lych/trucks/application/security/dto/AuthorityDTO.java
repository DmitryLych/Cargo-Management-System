package lych.trucks.application.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

/**
 * Authority dto.
 */
@Data
@JsonTypeName(value = "authorities")
public class AuthorityDTO implements Serializable {

    private static final long serialVersionUID = 6014774421356622804L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String authority;

    @JsonProperty
    private Integer userId;
}
