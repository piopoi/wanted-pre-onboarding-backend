package com.wanted.api.recruit.service;

import static com.wanted.api.recruit.controller.RecruitControllerTest.createRecruitCreateRequest;
import static com.wanted.api.recruit.controller.RecruitControllerTest.createRecruitUpdateRequest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wanted.api.common.BaseTest;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RecruitServiceTest extends BaseTest {

    @Autowired
    private RecruitService recruitService;
    @Autowired
    private RecruitRepository recruitRepository;

    private Long recruitId;

    @BeforeEach
    void setUp() {
        RecruitCreateRequest request = createRecruitCreateRequest();
        recruitId = recruitService.createRecruit(request);
    }

    @Test
    @DisplayName("채용공고를 등록할 수 있다.")
    void createRecruit() {
        //then
        Recruit findRecruit = findRecruitById(recruitId);
        assertThat(findRecruit).isNotNull();
        assertThat(findRecruit.getId()).isEqualTo(recruitId);
    }

    @Test
    @DisplayName("채용공고를 수정할 수 있다.")
    void updateRecruit() {
        //given
        RecruitUpdateRequest request = createRecruitUpdateRequest();

        //when
        recruitService.updateRecruit(recruitId, request);

        //then
        Recruit updatedRecruit = findRecruitById(recruitId);
        assertThat(updatedRecruit.getPosition()).isEqualTo(request.getPosition());
        assertThat(updatedRecruit.getReward()).isEqualTo(request.getReward());
        assertThat(updatedRecruit.getSkill()).isEqualTo(request.getSkill());
        assertThat(updatedRecruit.getContent()).isEqualTo(request.getContent());
    }

    @Test
    @DisplayName("채용공고를 삭제할 수 있다.")
    void deleteRecruit() {
        //when
        recruitService.deleteRecruit(recruitId);

        //then
        assertThatThrownBy(() -> findRecruitById(recruitId))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Recruit findRecruitById(Long recruitId) {
        return recruitRepository.findById(recruitId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 회사입니다."));
    }
}
