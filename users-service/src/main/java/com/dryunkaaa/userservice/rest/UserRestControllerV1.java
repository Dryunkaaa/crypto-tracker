package com.dryunkaaa.userservice.rest;

import java.util.List;
import java.util.UUID;

import com.dryunkaaa.userservice.dto.CreateUserDTO;
import com.dryunkaaa.userservice.dto.UserDTO;
import com.dryunkaaa.userservice.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserRestControllerV1 {

    // TODO: add password field and security
    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable(name = "id") UUID id) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable(name = "id") UUID id) {
        userService.softDelete(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<UserDTO> create(@RequestBody @Valid CreateUserDTO createUserDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.create(createUserDTO));
    }

}
