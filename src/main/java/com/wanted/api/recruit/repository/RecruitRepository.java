package com.wanted.api.recruit.repository;

import com.wanted.api.recruit.domain.Recruit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RecruitRepository extends JpaRepository<Recruit, Long> {

    @Query("SELECT r.id FROM Recruit r WHERE r.company.id = :companyId")
    List<Long> findIdsByCompanyId(Long companyId);
}
