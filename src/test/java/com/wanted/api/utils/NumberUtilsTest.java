package com.wanted.api.utils;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NumberUtilsTest {

    @Test
    @DisplayName("문자열이 정수인지 확인할 수 있다.")
    void isNumberic() {
        //given
        String str = "123";

        //when
        boolean result = NumberUtils.isNumberic(str);

        //then
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("invalid: 문자열이 정수인지 확인할 수 있다.")
    void isNumberic_invalid() {
        //given
        String str = " 123";

        //when
        boolean result = NumberUtils.isNumberic(str);

        //then
        assertThat(result).isFalse();
    }
}
