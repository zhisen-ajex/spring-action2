package com.zs.user.management.service;

import com.zs.user.management.service.async.ISinavSonuc;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@Service
@RequiredArgsConstructor
public class SinavSonucService {

    private final ISinavSonuc sonuc;
}
