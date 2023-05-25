package com.zs.user.management.service.async;


import com.zs.user.management.dto.UserDto;

import java.util.Map;

public interface IAuthService {
    public Map<String, String> authToken(UserDto dto);
}
