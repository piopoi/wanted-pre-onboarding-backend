package com.wanted.api.application.repository;

import com.wanted.api.application.domain.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    long countByMemberIdAndRecruitId(Long memberId, Long recruitId);
}
