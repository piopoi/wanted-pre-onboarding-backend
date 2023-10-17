package com.wanted.api.application.controller;

import com.wanted.api.application.service.ApplicationService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class ApplicationController {

    private final ApplicationService applicationService;

    @PostMapping("/{memberId}/{recruitId}")
    public ResponseEntity<Void> createApplication(@PathVariable Long memberId,
                                                  @PathVariable Long recruitId) {
        Long applicationId = applicationService.createApplication(memberId, recruitId);
        return ResponseEntity.created(URI.create("/api/applications/" + applicationId)).build();
    }
}
