package com.wanted.api.application.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.wanted.api.common.BaseControllerTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;

class ApplicationControllerTest extends BaseControllerTest {

    private final String requestUri = "/api/applications";

    @Test
    @DisplayName("사용자가 채용공고에 지원할 수 있다.")
    void createApplication() throws Exception {
        //given
        Long memberId = 1L;
        Long recruitId = addRecruit(1L, "백엔드 개발자", 100000L, "백엔드 개발자 채용합니다.", "spring mysql");

        //when then
        mockMvc.perform(RestDocumentationRequestBuilders.post(requestUri + "/{memberId}/{recruitId}", memberId, recruitId))
                .andDo(print())
                .andExpect(status().isCreated())
                .andDo(document("application/create",
                        pathParameters(
                                parameterWithName("memberId").description("사용자 아이디"),
                                parameterWithName("recruitId").description("채용공고 아이디")
                        ))
                );
    }
}
