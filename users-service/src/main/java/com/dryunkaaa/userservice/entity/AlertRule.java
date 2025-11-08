package com.dryunkaaa.userservice.entity;

import java.math.BigDecimal;

import com.dryunkaaa.userservice.enums.AlertRuleOperator;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name = "alert_rules", schema = "users")
@Getter
@Setter
public class AlertRule extends BaseEntity {

    @Column(name = "symbol", length = 10, nullable = false)
    private String symbol;

    @Column(name = "operator", length = 2, nullable = false)
    @Enumerated(EnumType.STRING)
    private AlertRuleOperator operator;

    @ColumnDefault(value = "true")
    @Column(name = "active", nullable = false)
    private Boolean active;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Column(name = "threshold", nullable = false)
    private BigDecimal threshold;

}
