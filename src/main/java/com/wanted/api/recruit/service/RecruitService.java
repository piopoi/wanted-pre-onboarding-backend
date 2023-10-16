package com.wanted.api.recruit.service;

import com.wanted.api.company.domain.Company;
import com.wanted.api.company.repository.CompanyRepository;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public Long createRecruit(RecruitCreateRequest recruitCreateRequest) {
        Company company = companyRepository.findById(recruitCreateRequest.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
        Recruit recruit = Recruit.of(recruitCreateRequest, company);
        return recruitRepository.save(recruit).getId();
    }

    @Transactional
    public void updateRecruit(Long recruitId, RecruitUpdateRequest request) {
        Recruit recruit = findRecruitById(recruitId);
        recruit.update(request.toEntity());
    }

    @Transactional(readOnly = true)
    public Recruit findRecruitById(Long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
    }
}
