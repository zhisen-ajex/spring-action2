package com.zs.user.management.repository;

import com.zs.user.management.model.UserAraGorev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserAraGorevRepository extends JpaRepository<UserAraGorev, UUID> {
}
