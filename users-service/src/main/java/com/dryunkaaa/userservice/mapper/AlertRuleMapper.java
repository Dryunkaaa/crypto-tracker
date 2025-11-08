package com.dryunkaaa.userservice.mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.dryunkaaa.userservice.dto.AlertRuleDTO;
import com.dryunkaaa.userservice.dto.CreateAlertRuleDTO;
import com.dryunkaaa.userservice.entity.AlertRule;
import com.dryunkaaa.userservice.util.DateTimeUtil;
import lombok.Setter;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
@Setter(onMethod_ = @Autowired)
public abstract class AlertRuleMapper {

    protected DateTimeUtil dateTimeUtil;

    public abstract AlertRuleDTO from(AlertRule alertRule);

    @Mapping(target = "active", expression = "java(true)")
    @Mapping(target = "createdAt", expression = "java(dateTimeUtil.now())")
    @Mapping(target = "updatedAt", expression = "java(dateTimeUtil.now())")
    public abstract AlertRule to(CreateAlertRuleDTO alertRuleDTO);

}
