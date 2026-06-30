package edu.fu.mapper;

import edu.fu.dto.UserRequest;
import edu.fu.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper { // UserMapperImpl

    @Mapping(source = "password", target = "passwordHash")
    User toEntity(UserRequest userRequest);
}
