package com.wanted.api.common;

import com.wanted.api.company.domain.Company;
import com.wanted.api.company.repository.CompanyRepository;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@ActiveProfiles("test")
@SpringBootTest
@Sql(scripts = {"/test.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class BaseTest {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RecruitRepository recruitRepository;

    protected Long addRecruit(Long companyId, String position, Long reward, String content, String skill) {
        RecruitCreateRequest request = new RecruitCreateRequest(companyId, position, reward, content, skill);
        Company company = companyRepository.findById(companyId).orElseThrow();
        Recruit recruit = Recruit.of(request, company);
        return recruitRepository.save(recruit).getId();
    }
}
