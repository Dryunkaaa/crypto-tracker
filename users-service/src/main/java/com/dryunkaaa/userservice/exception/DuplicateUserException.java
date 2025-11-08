package com.dryunkaaa.userservice.exception;

import lombok.Getter;

@Getter
public class DuplicateUserException extends UserException {

    private final String email;

    public DuplicateUserException(String email) {
        super("User with email %s already exists".formatted(email));
        this.email = email;
    }

}
