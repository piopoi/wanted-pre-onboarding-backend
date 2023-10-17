package com.wanted.api.application.domain;

import com.wanted.api.common.domain.BaseEntity;
import com.wanted.api.member.domain.Member;
import com.wanted.api.recruit.domain.Recruit;
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
@EqualsAndHashCode(callSuper = false)
public class Application extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recruit_id", nullable = false)
    private Recruit recruit;

    protected Application() {
    }

    @Builder(access = AccessLevel.PRIVATE)
    private Application(Member member, Recruit recruit) {
        this.member = member;
        this.recruit = recruit;
    }

    public static Application of(Member member, Recruit recruit) {
        return Application.builder()
                .member(member)
                .recruit(recruit)
                .build();
    }
}
