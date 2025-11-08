package com.dryunkaaa.userservice.repository;

import java.util.List;
import java.util.UUID;

import com.dryunkaaa.userservice.entity.AlertRule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRuleRepository extends JpaRepository<AlertRule, UUID> {

    List<AlertRule> findAllByUserId(UUID userId);

}
