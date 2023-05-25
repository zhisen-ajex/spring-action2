package com.zs.user.management.service.impl;

import com.zs.user.management.dto.SinavDto;
import com.zs.user.management.model.Sinav;
import com.zs.user.management.repository.SinavRepository;
import com.zs.user.management.service.async.ISinav;
import com.zs.user.management.utils.MapperUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class SinavServiceImpl implements ISinav {

    private final SinavRepository repository;
    private final MapperUtils mapper;

    @Override
    public List<SinavDto> getAllSinav() {
        log.info("getAll Sinav!");
        return mapper.mapAll(repository.findAll(), SinavDto.class);
    }

    @Override
    public SinavDto createSinav(SinavDto dto) {
        var sinav = repository.save(mapper.map(dto, Sinav.class));
        if (sinav.getSinavId() != null) {
            log.info("Created Sinav: " + sinav.getSinavId());
            return mapper.map(sinav, SinavDto.class);
        }
        return null;
    }

    @Override
    public SinavDto updateSinav(String sinavId, SinavDto dto) {
        var sinavs = repository.findById(UUID.fromString(sinavId));

        var sinavToUpdate = sinavs.map(val -> {
            val.setSinavAdi(dto.getSinavAdi() != null ? dto.getSinavAdi() : val.getSinavAdi());
            val.setSinavTarihi(dto.getSinavTarihi() != null ? dto.getSinavTarihi() : val.getSinavTarihi());
            val.setSinavYayinda(dto.getSinavYayinda() != null ? dto.getSinavYayinda() : val.getSinavYayinda());
            return val;
        }).orElseThrow(IllegalArgumentException::new);

        var sinav = repository.save(sinavToUpdate);
        log.info("Sinav Updated: " + sinavId);
        return mapper.map(sinav, SinavDto.class);
    }

    @Override
    public void deleteSinav(String sinavId) {
        repository.deleteById(UUID.fromString(sinavId));
        log.info("Sinav Deleted: " + sinavId);
    }
}
