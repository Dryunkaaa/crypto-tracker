package com.dryunkaaa.userservice.mapper;

import static org.mapstruct.InjectionStrategy.CONSTRUCTOR;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import com.dryunkaaa.userservice.dto.CreateUserDTO;
import com.dryunkaaa.userservice.dto.UserDTO;
import com.dryunkaaa.userservice.entity.User;
import com.dryunkaaa.userservice.util.DateTimeUtil;
import lombok.Setter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = SPRING, injectionStrategy = CONSTRUCTOR)
@Setter(onMethod_ = @Autowired)
public abstract class UserMapper {

    protected DateTimeUtil dateTimeUtil;

    public abstract UserDTO from(User user);

    @Mapping(target = "createdAt", expression = "java(dateTimeUtil.now())")
    @Mapping(target = "updatedAt", expression = "java(dateTimeUtil.now())")
    public abstract User to(CreateUserDTO createUserDTO);

}
