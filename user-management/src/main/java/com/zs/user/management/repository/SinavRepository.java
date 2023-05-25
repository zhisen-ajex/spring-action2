package com.zs.user.management.repository;

import com.zs.user.management.model.Sinav;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SinavRepository extends JpaRepository<Sinav, UUID> {
}
