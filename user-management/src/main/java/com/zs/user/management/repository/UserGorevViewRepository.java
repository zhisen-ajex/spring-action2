package com.zs.user.management.repository;

import com.zs.user.management.model.UserGorevView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserGorevViewRepository extends JpaRepository<UserGorevView, String> {

    @Query("select v.seviyeSize from UserGorevView v where v.tc= :tc")
    List<Long> getByTc(Long tc);
}
