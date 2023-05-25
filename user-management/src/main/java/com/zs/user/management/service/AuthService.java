package com.zs.user.management.service;

import com.zs.user.management.service.async.IAuthService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class AuthService {

    private final IAuthService authService;

}
