package com.wanted.api.application.exception;

public class AlreadyAppliedException extends RuntimeException {
    public AlreadyAppliedException() {
        super("이미 지원한 채용공고입니다.");
    }
}
