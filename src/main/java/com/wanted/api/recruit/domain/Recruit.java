package com.wanted.api.recruit.domain;

import com.wanted.api.common.domain.BaseEntity;
import com.wanted.api.company.domain.Company;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Entity
@Getter
@ToString
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String position;

    @Column
    private Long reward;

    @Column(nullable = false)
    private String content;

    @Column
    private String skill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private Company company;

    protected Recruit() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Recruit(String position, Long reward, String content, String skill, Company company) {
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.skill = skill;
        this.company = company;
    }

    public static Recruit of(RecruitCreateRequest recruitCreateRequest, Company company) {
        return Recruit.builder()
                .position(recruitCreateRequest.getPosition())
                .reward(recruitCreateRequest.getReward())
                .content(recruitCreateRequest.getContent())
                .skill(recruitCreateRequest.getSkill())
                .company(company)
                .build();
    }
}
