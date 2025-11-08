package com.dryunkaaa.userservice.exception.handler;

import com.dryunkaaa.userservice.exception.DuplicateUserException;
import com.dryunkaaa.userservice.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(UserNotFoundException e) {
        log.info("User not found by id [{}]", e.getId());
        // TODO: return body
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(DuplicateUserException.class)
    public ResponseEntity<?> handleDuplicateUserException(DuplicateUserException e) {
        // TODO: return body
        log.info("Attempt to create user with duplicated email [{}]", e.getEmail());
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    // TODO: add handling of validation exception

}
