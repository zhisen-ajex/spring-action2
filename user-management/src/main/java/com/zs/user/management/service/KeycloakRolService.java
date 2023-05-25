package com.zs.user.management.service;

import com.zs.user.management.service.async.IKeycloakRolService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class KeycloakRolService {

    private final IKeycloakRolService rol;
}
