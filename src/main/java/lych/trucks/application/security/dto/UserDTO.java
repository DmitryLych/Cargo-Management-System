package lych.trucks.application.security.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User dto.
 */
@Data
@JsonTypeName(value = "user")
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 6486538658780862471L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private List<AuthorityDTO> authorities;
}
