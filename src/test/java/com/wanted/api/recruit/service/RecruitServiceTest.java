package com.wanted.api.recruit.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.wanted.api.common.BaseTest;
import com.wanted.api.company.domain.Company;
import com.wanted.api.company.repository.CompanyRepository;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitGetDetailResponse;
import com.wanted.api.recruit.dto.RecruitGetResponse;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class RecruitServiceTest extends BaseTest {

    @Autowired
    private RecruitService recruitService;
    @Autowired
    private RecruitRepository recruitRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @DisplayName("채용공고를 등록할 수 있다.")
    void createRecruit() {
        //given
        RecruitCreateRequest request = new RecruitCreateRequest(1L, "포지션", 100L, "내용", "기술");

        //when
        Long createdRecruitId = recruitService.createRecruit(request);

        //then
        Recruit findRecruit = findRecruitById(createdRecruitId);
        assertThat(findRecruit).isNotNull();
        assertThat(findRecruit.getId()).isEqualTo(createdRecruitId);
    }

    @Test
    @DisplayName("채용공고를 검색하여 목록을 조회할 수 있다.")
    void searchRecruit() {
        //given
        String keyword = "keyword";
        addRecruit(4L, keyword + "주니어 개발자", 1500000L, "백엔드 채용함.", "Java");
        addRecruit(3L, "백엔드 개발자", 100000L, "채용합니다. " + keyword, "Python");
        addRecruit(1L, "개발자", 10000L, "채용합니다.", "Python");

        //when
        List<RecruitGetResponse> recruitGetResponses = recruitService.searchRecruit(keyword);

        //then
        assertThat(recruitGetResponses.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("채용공고 전체를 조회할 수 있다.")
    void getAllRecruits() {
        addRecruit(1L, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python, Java");
        addRecruit(2L, "프론트엔드 개발자", 500000L, "원티드코리아에서 프론트엔드 개발자를 환영합니다.", "React");
        addRecruit(3L, "네이버 검색 서비스 백엔드 개발자", 300000L, "네이버 검색 서비스 담당자 채용합니다.", "java spring jpa");
        addRecruit(4L, "카카오 선물하기팀 백엔드 개발자", 200000L, "카카오톡 선물하기 백엔드 구인합니다.", "java spring mybatis oracle");

        //when
        List<RecruitGetResponse> recruitGetResponses = recruitService.getAllRecruits();

        //then
        assertThat(recruitGetResponses.size()).isEqualTo(4);
    }

    @Test
    @DisplayName("채용공고 1건을 상세조회할 수 있다.")
    void getRecruit() {
        //given
        Long recruitId = addRecruit(1L, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python, Java");

        //when
        RecruitGetDetailResponse recruitGetDetailResponse = recruitService.getRecruit(recruitId);

        //then
        assertThat(recruitGetDetailResponse).isNotNull();
        assertThat(recruitGetDetailResponse.getRecruitId()).isEqualTo(recruitId);
        assertThat(recruitGetDetailResponse.getPosition()).isEqualTo("백엔드 주니어 개발자");
        assertThat(recruitGetDetailResponse.getReward()).isEqualTo(1500000L);
        assertThat(recruitGetDetailResponse.getCompanyName()).isEqualTo("원티드랩");
    }

    @Test
    @DisplayName("채용공고를 수정할 수 있다.")
    void updateRecruit() {
        //given
        Long recruitId = addRecruit(1L, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python, Java");
        RecruitUpdateRequest request = new RecruitUpdateRequest("포지션", 100L, "내용", "기술");

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
        //given
        Long recruitId = addRecruit(1L, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python, Java");

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

    private Long addRecruit(Long companyId, String position, Long reward, String content, String skill) {
        RecruitCreateRequest request = new RecruitCreateRequest(companyId, position, reward, content, skill);
        Company company = companyRepository.findById(companyId).orElseThrow();
        Recruit recruit = Recruit.of(request, company);
        return recruitRepository.save(recruit).getId();
    }
}
