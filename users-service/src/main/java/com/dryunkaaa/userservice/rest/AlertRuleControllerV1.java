package com.dryunkaaa.userservice.rest;

import java.util.List;
import java.util.UUID;

import com.dryunkaaa.userservice.dto.AlertRuleDTO;
import com.dryunkaaa.userservice.dto.CreateAlertRuleDTO;
import com.dryunkaaa.userservice.service.AlertRuleService;
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
// TODO: remove userID from the path and replace with user from the JWT token
@RequestMapping("/v1/users/{userId}/rules")
@RequiredArgsConstructor
public class AlertRuleControllerV1 {

    private final AlertRuleService alertRuleService;

    @GetMapping
    public ResponseEntity<List<AlertRuleDTO>> findAll(@PathVariable("userId") UUID userId) {
        return ResponseEntity.ok(alertRuleService.findAll(userId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlertRuleDTO> findById(@PathVariable("userId") UUID userId, @PathVariable("id") UUID id) {
        return ResponseEntity.ok(alertRuleService.find(id, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("userId") UUID userId, @PathVariable("id") UUID id) {
        alertRuleService.delete(id, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<AlertRuleDTO> add(
            @PathVariable("userId") UUID userId,
            @Valid @RequestBody CreateAlertRuleDTO createAlertRuleDTO
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(alertRuleService.add(createAlertRuleDTO, userId));
    }

}
