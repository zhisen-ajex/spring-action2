package com.zs.user.management.service.async;


import com.zs.user.management.dto.SinavDto;

import java.util.List;

public interface ISinav {

    public List<SinavDto> getAllSinav();

    public SinavDto createSinav(SinavDto dto);

    public SinavDto updateSinav(String sinavId, SinavDto dto);

    public void deleteSinav(String sinavId);
}
