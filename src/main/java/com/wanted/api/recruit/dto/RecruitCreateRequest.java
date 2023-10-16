package com.wanted.api.recruit.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class RecruitCreateRequest {

    @NotNull
    private Long companyId;

    @NotBlank
    private String position;

    @NotNull
    private Long reward;

    @NotBlank
    private String content;

    @NotBlank
    private String skill;
}
