package com.dryunkaaa.userservice.dto;

import java.math.BigDecimal;

import com.dryunkaaa.userservice.enums.AlertRuleOperator;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateAlertRuleDTO(
        @NotBlank @Size(max = 10) String symbol,
        AlertRuleOperator operator,
        @NotNull @DecimalMin("0.00") @Digits(integer = 38, fraction = 2)
        BigDecimal threshold
) {
}
