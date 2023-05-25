package com.zs.user.management.service;

import com.zs.user.management.service.async.IKeycloakUserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class KeycloakUserService {

    private final IKeycloakUserService userService;
}
