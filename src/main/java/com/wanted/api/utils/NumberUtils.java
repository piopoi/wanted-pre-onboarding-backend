package com.wanted.api.utils;

public class NumberUtils {

    /**
     * 문자열이 정수인지 확인
     * @return 정수이면 true, 아니면 false.
     */
    public static boolean isNumberic(String str) {
        return str.chars().allMatch(Character::isDigit);
    }
}
