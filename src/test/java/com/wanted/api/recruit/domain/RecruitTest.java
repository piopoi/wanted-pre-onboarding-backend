package com.wanted.api.recruit.domain;

import static org.assertj.core.api.Assertions.*;

import com.wanted.api.common.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RecruitTest extends BaseTest {

    @Test
    @DisplayName("채용공고를 수정할 수 있다.")
    void update() {
        //given
        Recruit recruit = Recruit.of("포지션", 1L, "내용", "스킬");
        Recruit updateRecruit = Recruit.of("수정된 포지션", 1L, "수정된 내용", "수정된 스킬");

        //when
        recruit.update(updateRecruit);

        //then
        assertThat(recruit.getPosition()).isEqualTo(updateRecruit.getPosition());
        assertThat(recruit.getReward()).isEqualTo(updateRecruit.getReward());
        assertThat(recruit.getContent()).isEqualTo(updateRecruit.getContent());
        assertThat(recruit.getSkill()).isEqualTo(updateRecruit.getSkill());
    }

    @Test
    @DisplayName("데이터가 잘못되었거나 없으면 채용공고를 수정할 수 없다.")
    void update_invalid() {
        //given
        Recruit recruit = Recruit.of("포지션", 1L, "내용", "스킬");
        Recruit updateRecruit = Recruit.of(null, -1L, null, null);

        //when
        recruit.update(updateRecruit);

        //then
        assertThat(recruit.getPosition()).isEqualTo("포지션");
        assertThat(recruit.getReward()).isEqualTo(1L);
        assertThat(recruit.getContent()).isEqualTo("내용");
        assertThat(recruit.getSkill()).isEqualTo("스킬");
    }
}
