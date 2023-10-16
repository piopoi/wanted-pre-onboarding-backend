package com.wanted.api.recruit.controller;

import com.wanted.api.recruit.dto.RecruitCreateRequest;
import com.wanted.api.recruit.service.RecruitService;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping("/api/recruit")
    public ResponseEntity<Void> createRecruit(@RequestBody @Valid RecruitCreateRequest recruitCreateRequest) {
        Long recruitId = recruitService.createRecruit(recruitCreateRequest);
        return ResponseEntity.created(URI.create("/api/recruit" + recruitId)).build();
    }
}
