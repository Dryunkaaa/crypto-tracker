package com.dryunkaaa.userservice.repository;

import java.util.Optional;
import java.util.UUID;

import com.dryunkaaa.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    @Modifying
    @Query("""
            UPDATE User u SET u.deletedAt = CURRENT_TIMESTAMP WHERE u.id = :id
            """)
    void softDelete(UUID id);

}
