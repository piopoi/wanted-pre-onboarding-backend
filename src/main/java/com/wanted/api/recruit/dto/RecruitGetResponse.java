package com.wanted.api.recruit.dto;

import com.wanted.api.recruit.domain.Recruit;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class RecruitGetResponse {

    private final Long recruitId;
    private final String companyName;
    private final String country;
    private final String city;
    private final String position;
    private final Long reward;
    private final String skill;

    public static RecruitGetResponse from(Recruit recruit) {
        return RecruitGetResponse.builder()
                .recruitId(recruit.getId())
                .companyName(recruit.getCompany().getName())
                .country(recruit.getCompany().getCountry())
                .city(recruit.getCompany().getCity())
                .position(recruit.getPosition())
                .reward(recruit.getReward())
                .skill(recruit.getSkill())
                .build();
    }
}
