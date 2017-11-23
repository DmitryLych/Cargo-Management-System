package lych.trucks.application.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonTypeName(value = "company")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NONE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CompanyResponse implements Serializable {

    private static final long serialVersionUID = 6590986439451007101L;

    @JsonProperty
    private Integer id;

    @JsonProperty
    private String companyName;

    @JsonProperty
    private String address;

    @JsonProperty
    private String email;

    @JsonProperty
    private String telephoneNumber;
}
