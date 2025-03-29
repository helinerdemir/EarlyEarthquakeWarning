package com.example.mapper;

import com.example.dto.user.UserDto;
import com.example.entity.User;
import com.example.mapper.base.BaseMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDto> {
    @Mapping(target = "password", ignore = true)
    @Override
    UserDto toDto(User entity);

    @Mapping(target = "password", ignore = true)
    @Override
    void updateEntity(@MappingTarget User entity, UserDto dto);
} 