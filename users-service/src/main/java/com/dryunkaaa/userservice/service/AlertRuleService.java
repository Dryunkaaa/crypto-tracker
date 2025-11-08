package com.dryunkaaa.userservice.service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.dryunkaaa.userservice.dto.AlertRuleDTO;
import com.dryunkaaa.userservice.dto.CreateAlertRuleDTO;
import com.dryunkaaa.userservice.entity.AlertRule;
import com.dryunkaaa.userservice.entity.User;
import com.dryunkaaa.userservice.mapper.AlertRuleMapper;
import com.dryunkaaa.userservice.repository.AlertRuleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlertRuleService {

    private final AlertRuleRepository alertRuleRepository;
    private final AlertRuleMapper mapper;

    public List<AlertRuleDTO> findAll(UUID userId) {
        // TODO: check userId or not?
        return alertRuleRepository.findAllByUserId(userId)
                .stream()
                .map(mapper::from)
                .toList();
    }

    public AlertRuleDTO find(UUID id, UUID userId) {
        var alertRule = alertRuleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException());  // todo: custom exception

        if (!Objects.equals(alertRule.getUser().getId(), userId)) {
            // todo: check user id? in case if this is a rule of another user -> throw forbidden?
            // todo; currently 2 DB calls. think about just a single one with join
        }

        log.info("Alert rule with id [{}] successfully found", id);
        return mapper.from(alertRule);
    }

    public void delete(UUID id, UUID userId) {
        // TODO: check alert owner by userId
        alertRuleRepository.deleteById(id);
        log.info("Alert rule with id [{}] successfully deleted", id);
    }

    public AlertRuleDTO add(CreateAlertRuleDTO createAlertRuleDTO, UUID userId) {
        // TODO: validation of symbol using rate service
        // TODO: check if rule is unique
        var savedRule = create(createAlertRuleDTO, userId);

        log.info("Added new alert rule [{}] (symbol: [{}], operator: [{}], threshold: [{}]) by user [{}]",
                savedRule.getId(), savedRule.getSymbol(), savedRule.getOperator(), savedRule.getThreshold(), userId);

        return mapper.from(savedRule);
    }

    private AlertRule create(CreateAlertRuleDTO createAlertRuleDTO, UUID userId) {
        var alertRule = mapper.to(createAlertRuleDTO);

        var newUser = new User();
        newUser.setId(userId);
        alertRule.setUser(newUser);

        return alertRuleRepository.save(alertRule);
    }

}
