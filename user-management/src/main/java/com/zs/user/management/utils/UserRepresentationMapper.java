package com.zs.user.management.utils;

import org.keycloak.representations.idm.UserRepresentation;
import org.mapstruct.Mapper;

@Mapper
public interface UserRepresentationMapper {

    UserRepresentation destination (UserRepresentation source);
}

