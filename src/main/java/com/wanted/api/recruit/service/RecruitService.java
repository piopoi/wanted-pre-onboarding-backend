package com.wanted.api.recruit.service;

import com.wanted.api.company.domain.Company;
import com.wanted.api.company.repository.CompanyRepository;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitGetResponse;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import com.wanted.api.utils.NumberUtils;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
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
    private final EntityManager entityManager;

    @Transactional
    public Long createRecruit(RecruitCreateRequest recruitCreateRequest) {
        Company company = companyRepository.findById(recruitCreateRequest.getCompanyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
        Recruit recruit = Recruit.of(recruitCreateRequest, company);
        return recruitRepository.save(recruit).getId();
    }

    @Transactional(readOnly = true)
    public List<RecruitGetResponse> searchRecruit(String keyword) {
        List<Recruit> recruits = searchRecruitByKeyword(keyword);
        return recruits.stream()
                .map(RecruitGetResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<RecruitGetResponse> getAllRecruits() {
        List<Recruit> recruits = recruitRepository.findAll();
        return recruits.stream()
                .map(RecruitGetResponse::from)
                .toList();
    }

    @Transactional
    public void updateRecruit(Long recruitId, RecruitUpdateRequest request) {
        Recruit recruit = findRecruitById(recruitId);
        recruit.update(request.toEntity());
    }

    @Transactional
    public void deleteRecruit(Long recruitId) {
        findRecruitById(recruitId);
        recruitRepository.deleteById(recruitId);
    }

    private List<Recruit> searchRecruitByKeyword(String keyword) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Recruit> query = criteriaBuilder.createQuery(Recruit.class);

        Root<Recruit> recruitRoot = query.from(Recruit.class);
        Join<Recruit, Company> companyJoin = recruitRoot.join("company", JoinType.INNER);

        List<Predicate> predicates = new ArrayList<>();

        // Add conditions for Recruit
        predicates.add(criteriaBuilder.like(recruitRoot.get("position"), "%" + keyword + "%"));
        predicates.add(criteriaBuilder.like(recruitRoot.get("skill"), "%" + keyword + "%"));
        predicates.add(criteriaBuilder.like(recruitRoot.get("content"), "%" + keyword + "%"));
        if (NumberUtils.isNumberic(keyword)) {
            predicates.add(criteriaBuilder.equal(recruitRoot.get("reward"), Long.parseLong(keyword)));
        }

        // Add conditions for Company
        predicates.add(criteriaBuilder.like(companyJoin.get("name"), "%" + keyword + "%"));
        predicates.add(criteriaBuilder.like(companyJoin.get("country"), "%" + keyword + "%"));
        predicates.add(criteriaBuilder.like(companyJoin.get("city"), "%" + keyword + "%"));

        query.select(recruitRoot).where(criteriaBuilder.or(predicates.toArray(new Predicate[0])));
        return entityManager.createQuery(query).getResultList();
    }

    private Recruit findRecruitById(Long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채용공고입니다."));
    }
}
