package com.dryunkaaa.userservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record CreateUserDTO(
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Email format is invalid")
        // TODO: max length validation
        String email
) {
}
