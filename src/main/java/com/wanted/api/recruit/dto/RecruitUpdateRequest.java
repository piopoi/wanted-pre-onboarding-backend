package com.wanted.api.recruit.dto;

import com.wanted.api.recruit.domain.Recruit;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RecruitUpdateRequest {

    @NotBlank
    private String position;

    @NotNull
    private Long reward;

    @NotBlank
    private String content;

    @NotBlank
    private String skill;

    public Recruit toEntity() {
        return Recruit.of(position, reward, content, skill);
    }
}
