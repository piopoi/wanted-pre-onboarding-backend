package com.wanted.api.recruit.dto;

import com.wanted.api.recruit.domain.Recruit;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RecruitGetDetailResponse {

    private final Long recruitId;
    private final String companyName;
    private final String country;
    private final String city;
    private final String position;
    private final Long reward;
    private final String skill;
    private final String content;
    private final List<Long> otherRecruitIds;

    public static RecruitGetDetailResponse from(Recruit recruit, List<Long> otherRecruitIds) {
        return RecruitGetDetailResponse.builder()
                .recruitId(recruit.getId())
                .companyName(recruit.getCompany().getName())
                .country(recruit.getCompany().getCountry())
                .city(recruit.getCompany().getCity())
                .position(recruit.getPosition())
                .reward(recruit.getReward())
                .skill(recruit.getSkill())
                .content(recruit.getContent())
                .otherRecruitIds(otherRecruitIds)
                .build();
    }
}
