package com.zs.user.management.service.async;

import com.zs.user.management.dto.SinavSonucDto;

import java.util.List;

public interface ISinavSonuc {

    public List<SinavSonucDto> getAllSonuc();

    public SinavSonucDto createSonuc(SinavSonucDto dto);

    public SinavSonucDto updateSonuc(String sonucId, SinavSonucDto dto);

    public void deleteSonuc(String sonucId);

    public List<SinavSonucDto>  getSonucBySinav(String id);
}
