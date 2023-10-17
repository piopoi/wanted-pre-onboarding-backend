package com.wanted.api.recruit.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.queryParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.api.common.BaseControllerTest;
import com.wanted.api.company.domain.Company;
import com.wanted.api.company.repository.CompanyRepository;
import com.wanted.api.recruit.domain.Recruit;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.dto.RecruitUpdateRequest;
import com.wanted.api.recruit.repository.RecruitRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

public class RecruitControllerTest extends BaseControllerTest {

    private final String requestUri = "/api/recruits";

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private RecruitRepository recruitRepository;

    @BeforeEach
    void setUp() {
        addRecruit(1L, "백엔드 주니어 개발자", 1500000L, "원티드랩에서 백엔드 주니어 개발자를 채용합니다.", "Python, Java");
        addRecruit(2L, "프론트엔드 개발자", 500000L, "원티드코리아에서 프론트엔드 개발자를 환영합니다.", "React");
        addRecruit(3L, "네이버 검색 서비스 백엔드 개발자", 300000L, "네이버 검색 서비스 담당자 채용합니다.", "java spring jpa");
        addRecruit(4L, "카카오 선물하기팀 백엔드 개발자", 200000L, "카카오톡 선물하기 백엔드 구인합니다.", "java spring mybatis oracle");
    }

    @Test
    @DisplayName("채용공고를 등록할 수 있다.")
    void createRecruit() throws Exception {
        //given
        RecruitCreateRequest request = new RecruitCreateRequest(1L, "포지션", 100000L, "내용", "java spring jpa");

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.post(requestUri)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("recruit/create",
                        requestFields(
                                fieldWithPath("companyId").description("채용 회사 아이디"),
                                fieldWithPath("position").description("채용 포지션"),
                                fieldWithPath("reward").description("채용 보상금"),
                                fieldWithPath("content").description("채용 내용"),
                                fieldWithPath("skill").description("사용 기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고를 검색하여 목록을 조회할 수 있다.")
    void searchRecruit() throws Exception {
        //given
        String keyword = "keyword";
        addRecruit(4L, keyword + "백엔드 주니어 개발자", 1500000L, "백엔드 주니어 개발자를 채용합니다.", "Java");
        addRecruit(3L, "백엔드 주니어 개발자", 100000L, "채용합니다. " + keyword, "Python");

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(requestUri + "/search?keyword=" + keyword)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andDo(document("recruit/search",
                        queryParameters(
                                parameterWithName("keyword").description("검색 키워드")
                        ),
                        responseFields(
                                fieldWithPath("[].recruitId").description("채용공고 아이디"),
                                fieldWithPath("[].companyName").description("채용 회사명"),
                                fieldWithPath("[].country").description("국가"),
                                fieldWithPath("[].city").description("지역(도시)"),
                                fieldWithPath("[].position").description("채용 포지션"),
                                fieldWithPath("[].reward").description("채용 보상금"),
                                fieldWithPath("[].skill").description("사용 기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고 전체를 조회할 수 있다.")
    void getAllRecruits() throws Exception {
        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.get(requestUri)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andDo(document("recruit/getAll",
                        responseFields(
                                fieldWithPath("[].recruitId").description("채용공고 아이디"),
                                fieldWithPath("[].companyName").description("채용 회사명"),
                                fieldWithPath("[].country").description("국가"),
                                fieldWithPath("[].city").description("지역(도시)"),
                                fieldWithPath("[].position").description("채용 포지션"),
                                fieldWithPath("[].reward").description("채용 보상금"),
                                fieldWithPath("[].skill").description("사용 기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고를 수정할 수 있다.")
    void updateRecruit() throws Exception {
        //given
        RecruitUpdateRequest request = new RecruitUpdateRequest("수정된 포지션", 500000L, "수정된 내용", "AI");

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.patch(requestUri + "/{recruitId}", 1L)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recruit/update",
                        pathParameters(
                                parameterWithName("recruitId").description("채용공고 아이디")
                        ),
                        requestFields(
                                fieldWithPath("position").description("채용 포지션"),
                                fieldWithPath("reward").description("채용 보상금"),
                                fieldWithPath("content").description("채용 내용"),
                                fieldWithPath("skill").description("사용 기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고를 삭제할 수 있다.")
    void deleteRecruit() throws Exception {
        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(requestUri + "/{recruitId}", 1L))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recruit/delete",
                        pathParameters(
                                parameterWithName("recruitId").description("채용공고 아이디")
                        ))
                );
    }

    private void addRecruit(Long companyId, String position, Long reward, String content, String skill) {
        RecruitCreateRequest request = new RecruitCreateRequest(companyId, position, reward, content, skill);
        Company company = companyRepository.findById(companyId).orElseThrow();
        Recruit recruit = Recruit.of(request, company);
        recruitRepository.save(recruit);
    }
}
