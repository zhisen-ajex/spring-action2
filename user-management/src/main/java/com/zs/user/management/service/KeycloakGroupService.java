package com.zs.user.management.service;

import com.zs.user.management.service.async.IKeycloakGroupService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class KeycloakGroupService {

    private final IKeycloakGroupService group;
}
