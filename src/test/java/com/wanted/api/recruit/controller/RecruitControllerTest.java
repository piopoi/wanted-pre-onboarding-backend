package com.wanted.api.recruit.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

    private Long recruitId;

    @BeforeEach
    void setUp() {
        Company company = companyRepository.findById(1L).orElseThrow();
        Recruit recruit = Recruit.of(createRecruitCreateRequest(), company);
        recruitId = recruitRepository.save(recruit).getId();
    }

    @Test
    @DisplayName("채용공고를 등록할 수 있다.")
    void createRecruit() throws Exception {
        //given
        RecruitCreateRequest request = createRecruitCreateRequest();

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
                                fieldWithPath("skill").description("사용기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고를 수정할 수 있다.")
    void updateRecruit() throws Exception {
        //given
        RecruitUpdateRequest request = createRecruitUpdateRequest();

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.patch(requestUri + "/{recruitId}", recruitId)
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
                                fieldWithPath("skill").description("사용기술")
                        ))
                );
    }

    @Test
    @DisplayName("채용공고를 삭제할 수 있다.")
    void deleteRecruit() throws Exception {
        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.delete(requestUri + "/{recruitId}", recruitId))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("recruit/delete",
                        pathParameters(
                                parameterWithName("recruitId").description("채용공고 아이디")
                        ))
                );
    }

    public static RecruitCreateRequest createRecruitCreateRequest() {
        return RecruitCreateRequest.builder()
                .companyId(1L)
                .position("백엔드 주니어 개발자")
                .reward(1000000L)
                .content("개발팀장 채용합니다.")
                .skill("spring boot, mysql")
                .build();
    }

    public static RecruitUpdateRequest createRecruitUpdateRequest() {
        return RecruitUpdateRequest.builder()
                .position("Django 백엔드 개발자")
                .reward(500000L)
                .content("Django 백엔드 개발자")
                .skill("spring boot, mysql")
                .build();
    }
}
