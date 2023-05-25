package com.zs.user.management.service;

import com.zs.user.management.service.async.ISinav;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class SinavService {

    private final ISinav sinav;
}
