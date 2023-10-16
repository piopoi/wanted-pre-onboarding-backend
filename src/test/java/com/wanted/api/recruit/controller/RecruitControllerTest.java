package com.wanted.api.recruit.controller;

import static org.springframework.http.MediaType.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.api.common.BaseControllerTest;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

class RecruitControllerTest extends BaseControllerTest {

    private final String requestUri = "/api/recruit";

    @Test
    @DisplayName("채용공고를 등록할 수 있다.")
    void createRecruit() throws Exception {
        //given
        RecruitCreateRequest request = RecruitCreateRequest.builder()
                .companyId(1L)
                .position("팀장")
                .reward(1000000L)
                .content("개발팀장 채용합니다.")
                .skill("spring boot, mysql")
                .build();

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
}
