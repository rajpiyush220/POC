package com.touchblankspot.auth.jwt.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.touchblankspot.auth.jwt.data.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
@JsonPropertyOrder({"username", "email", "password", "password", "contact_no", "roles"})
public class ApiUserRequest {
    @JsonProperty("username")
    @Size(min = 6, max = 50, message = "Minimum username length: 6 characters")
    @Schema(description = "Unique string")
    private String userName;

    @JsonProperty("email")
    @Size(min = 5, max = 60)
    @Email
    private String email;

    @Size(min = 8, message = "Minimum password length: 8 characters")
    @JsonProperty("password")
    private String password;

    @JsonProperty("contact_no")
    @Size(min = 10, max = 30)
    private String contactNo;

    @JsonProperty("roles")
    private Set<RoleEnum> roles;
}
