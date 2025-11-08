package com.dryunkaaa.userservice.dto;

import java.math.BigDecimal;
import java.util.UUID;

import com.dryunkaaa.userservice.enums.AlertRuleOperator;

public record AlertRuleDTO(UUID id, String symbol, BigDecimal threshold, AlertRuleOperator operator) {
}
