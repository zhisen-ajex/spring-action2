package com.zs.user.management.repository;

import com.zs.user.management.model.UserGorev;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserGorevRepository extends JpaRepository<UserGorev, UUID> {
}
