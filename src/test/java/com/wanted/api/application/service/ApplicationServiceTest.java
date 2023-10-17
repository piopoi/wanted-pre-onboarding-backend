package com.wanted.api.application.service;

import static org.assertj.core.api.Assertions.*;

import com.wanted.api.application.domain.Application;
import com.wanted.api.application.repository.ApplicationRepository;
import com.wanted.api.common.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ApplicationServiceTest extends BaseTest {

    @Autowired
    private ApplicationService applicationService;
    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    @DisplayName("사용자가 채용공고에 지원할 수 있다.")
    void createApplication() {
        //given
        Long memberId = 1L;
        Long recruitId = addRecruit(1L, "백엔드 개발자", 100000L, "백엔드 개발자 채용합니다.", "spring mysql");

        //when
        Long applicationId = applicationService.createApplication(memberId, recruitId);

        //then
        Application findApplication = findApplicationById(applicationId);
        assertThat(findApplication).isNotNull();
        assertThat(findApplication.getId()).isEqualTo(applicationId);
        assertThat(findApplication.getMember().getId()).isEqualTo(memberId);
        assertThat(findApplication.getRecruit().getId()).isEqualTo(recruitId);
    }

    private Application findApplicationById(Long applicationId) {
        return applicationRepository.findById(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 입사지원입니다."));
    }
}
