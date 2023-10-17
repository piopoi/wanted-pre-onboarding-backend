package com.wanted.api.recruit.domain;

import com.wanted.api.common.domain.BaseEntity;
import com.wanted.api.company.domain.Company;
import com.wanted.api.application.domain.Application;
import com.wanted.api.recruit.dto.RecruitCreateRequest;
import io.micrometer.common.util.StringUtils;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
@EqualsAndHashCode(callSuper = false)
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
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "recruit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application> applications = new ArrayList<>();

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

    public static Recruit of(String position, Long reward, String content, String skill) {
        return Recruit.builder()
                .position(position)
                .reward(reward)
                .content(content)
                .skill(skill)
                .build();
    }

    public void update(Recruit recruit) {
        updatePosition(recruit.getPosition());
        updateReward(recruit.getReward());
        updateContent(recruit.getContent());
        updateSkill(recruit.getSkill());
    }

    private void updatePosition(String position) {
        if (!StringUtils.isBlank(position)) {
            this.position = position;
        }
    }

    private void updateReward(Long reward) {
        if (reward != null && reward >= 0) {
            this.reward = reward;
        }
    }

    private void updateContent(String content) {
        if (!StringUtils.isBlank(content)) {
            this.content = content;
        }
    }

    private void updateSkill(String skill) {
        if (!StringUtils.isBlank(skill)) {
            this.skill = skill;
        }
    }
}
