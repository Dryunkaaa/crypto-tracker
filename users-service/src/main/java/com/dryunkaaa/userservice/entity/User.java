package com.dryunkaaa.userservice.entity;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users", schema = "users")
public class User extends BaseEntity {

    @Size(max = 1024)
    @Column(name = "email", unique = true, nullable = false, length = 1024)
    private String email;

    @Column(name = "deleted_at")
    private Instant deletedAt;

}
