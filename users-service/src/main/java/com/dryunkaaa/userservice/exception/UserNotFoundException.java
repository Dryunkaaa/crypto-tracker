package com.dryunkaaa.userservice.exception;

import java.util.UUID;

import lombok.Getter;

@Getter
public class UserNotFoundException extends UserException {

    private final UUID id;

    public UserNotFoundException(UUID id) {
        super("User with id %s not found!".formatted(id));
        this.id = id;
    }

}
